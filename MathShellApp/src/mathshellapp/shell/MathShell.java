/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathshellapp.shell;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import mathshellapp.shell.Function.Function;

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
    private final String RGX_FUNCTION_CREATION = "[a-zA-Z]\\(([a-z],)*[a-z]\\)=.+";
    private final String RGX_FUNCTION_CALL = "[a-zA-Z]\\((\\d+(\\.\\d+)?,)*\\d+(\\.\\d+)?\\)";
    private Map<String, Function> functions;
    
    public MathShell(){
        functions = new HashMap();
    }

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
            input = sc.nextLine().trim().replace(" ", "");

            if (input.matches(RGX_FUNCTION_CREATION)) {
                
                addFunction(input);
            }
            else if (input.matches(RGX_FUNCTION_CALL)){
                
                applyFunction(input);
            }
            

        } while (!input.equals(CMD_EXIT));

    }

    private void addFunction(String function) {
        Function f = new Function(function);
        String name = f.getName();
        functions.put(name, f);
    }
    
    private void applyFunction(String function){
        
        String name = function.substring(0, 1);
        String arguments = function.substring(2, function.length()-1);
        
        Function f = functions.get(name);
        
        if (f!= null){
            System.out.println(f.apply(arguments));
        }
        else{
            System.out.println("La funcion "+name+" no existe.");
        }
            
        
    }

}
