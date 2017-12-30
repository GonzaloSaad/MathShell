/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathshellapp.shell.Calculator;

import java.util.Scanner;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;

/**
 * Shell that will simulate a calculator.
 * It will not take variables.
 *
 * @author Gonzalo
 */
public class CalculatorShell {

    private final String SYM_PROMPT;
    private final String CMD_EXIT = "finish";

    public CalculatorShell(String mainPrompt) {
        SYM_PROMPT = "c" + mainPrompt;
    }

    public CalculatorShell() {
        this("$");
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String input;
        String command;
        String arguments;
        
        

        while (true) {
            System.out.print(SYM_PROMPT + " ");
            input = sc.nextLine().trim().replace(" ", "");

            if (input.equals(CMD_EXIT)) {
                break;
            }
            calculate(input);

        }

    }

    private void calculate(String input) {

        try {
            Expression e = new ExpressionBuilder(input).build();
            System.out.println(e.evaluate());
        } catch (UnknownFunctionOrVariableException ex) {
            System.out.println("Error de sintaxis, " + ex.getToken() + " no se reconoce, en la posicion " + ex.getPosition());

        } catch (ArithmeticException ex) {
            System.out.println("Division por cero!");
        }

    }
}
