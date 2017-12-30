/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathshellapp.shell;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.Expression;

/**
 * Class that will encapsulate all the shell behaviour.
 * 
 * 
 * @author Gonzalo
 */
public class MathShell {

    private String SYM_PROMPT = "$";
    private final String CMD_CLEAR = "clc";
    private final String CMD_EXIT = "exit";
    private final String RGX_FUNCTION = "[a-zA-Z]\\(([a-z],)*[a-z]\\)=.+";
    private Map<String, Expression> functions;

    /**
     * Makes the whole shell start running, shows a prompt and waits for
     * the user to input something.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        String input;
        String command;
        String arguments;
        

        do {
            System.out.print(SYM_PROMPT + " ");
            input = sc.nextLine().trim();

            if (Pattern.matches(RGX_FUNCTION,input)){
                addFunction(input);
            }
            

        } while (!input.equals(CMD_EXIT));

    }
    
    private void addFunction(String function){
        String header = function.split("=")[0];
        String name = header.substring(0, 1);
        String arguments = header.substring(2, header.length()-1);
        String expression = function.split("=")[1];
        
        System.out.println(name);
        System.out.println(arguments);
        System.out.println(expression);
        
        
        
        
    }
    
    

}
