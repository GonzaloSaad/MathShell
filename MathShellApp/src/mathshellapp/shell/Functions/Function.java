/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathshellapp.shell.Functions;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
/**
 *
 * Clase que simula 
 * 
 * @author Gonzalo
 */
public class Function {
    
    private String name;
    private String[] arguments;
    private String expressionString;
    private Expression expression;
    
    public Function(String function){
        String header = function.split("=")[0];
        name = header.substring(0, 1);
        arguments = header.substring(2, header.length()-1).split(",");
        expressionString = function.split("=")[1];
        
        this.createExpression();
    }
        
       
    private void createExpression(){
        ExpressionBuilder builder = new ExpressionBuilder(expressionString);
        for(String var :arguments){
            builder.variable(var);
        }
        expression = builder.build();
        
    }
    
    public double apply(Double values[]) throws IllegalArgumentException{
        
        int argCount = arguments.length;
        int valCount = values.length;
        
        if (valCount != argCount){
            throw new IllegalArgumentException("La function tiene "+argCount+", se le pasaron "+valCount +".");
        }
        
        for (int i = 0; i<argCount;i++){
            expression.setVariable(arguments[i], values[i]);
        }
        return expression.evaluate();
        
    }
    public double apply(String value) throws IllegalArgumentException{
        String[] values = value.split(",");
        int valCount = values.length;
        Double[] doublesValues = new Double[valCount];
        
        for (int i=0; i<valCount; i++){
            doublesValues[i]= Double.parseDouble(values[i]);
        }
        return this.apply(doublesValues);
    }

    public String getName() {
        return name;
    }
    
    
}
