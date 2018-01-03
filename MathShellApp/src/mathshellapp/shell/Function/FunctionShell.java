/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathshellapp.shell.Function;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import mathshellapp.shell.Functions;

/**
 *
 * @author Gonzalo
 */
public class FunctionShell {

    private final String SYM_PROMPT;
    private final String RGX_FUNCTION_CREATION = "[a-zA-Z][0-9]*\\(([a-z][0-9]*,)*[a-z][0-9]*\\)=.+";
    private final String RGX_FUNCTION_CALL = "[a-zA-Z]\\((\\d+(\\.\\d+)?,)*\\d+(\\.\\d+)?\\)";
    private final String RGX_COMPOSED_FUNCTION_CALL = "[a-zA-Z][0-9]*\\(([a-zA-Z][0-9]*\\()+((\\d+(\\.\\d+)?,)*\\d+(\\.\\d+)?)+\\)+";
    private final String RGX_COMPLEX_COMMAND = "[a-z]+(-[a-z]:[a-z]+)?";
    private final String CMD_EXIT = "finish";
    private final String CMD_FUNCTION_LIST = "list";
    private final String CMD_USER_FUNCTIONS = "listfun";
    private Map<String, Function> functions;

    public FunctionShell(String mainPrompt) {
        SYM_PROMPT = "f" + mainPrompt;
        functions = new HashMap();
    }

    public FunctionShell() {
        this("$");
    }

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

            } else if (input.matches(RGX_FUNCTION_CALL)) {

                applyFunction(input);
            }else if (input.matches(RGX_COMPOSED_FUNCTION_CALL)){
                //TODO!!!!
            } else if (input.matches(RGX_COMPLEX_COMMAND)) {
                operateCommand(input);
            }

        } while (!input.equals(CMD_EXIT));

    }

    private void addFunction(String function) {
        Function f = new Function(function);
        String name = f.getName();
        functions.put(name, f);
    }

    private void applyFunction(String function) {

        String name = function.substring(0, 1);
        String arguments = function.substring(2, function.length() - 1);

        Function f = functions.get(name);

        if (f == null) {
            System.out.println("La funcion " + name + " no existe.");
            return;
        }

        try {
            System.out.println(f.apply(arguments));
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void operateCommand(String input) {
        String cmd = input.split("-")[0];
        boolean args = false;
        String argument = "";

        if (!(cmd.length() == input.length())) {
            args = true;
            argument = input.split("-")[1];
        }

        switch (cmd) {
            case CMD_FUNCTION_LIST:
                listCommand(args, argument);
                break;
            case CMD_USER_FUNCTIONS:
                listfunCommand(args,argument);
                break;
        }
    }
    
    private void listfunCommand(boolean hasArgument, String argument){
        for (String function : functions.keySet()) {
            System.out.println(function + ":\t"+functions.get(function).toString());
        }
    }        

    private void listCommand(boolean hasArgument, String argument) {
        if (!hasArgument) {
            Functions.listFunction();
            return;
        }
        String option = argument.split(":")[0];

        if (option.length() == argument.length()) {
            System.out.println("No se especificaron argumentos.");
            return;
        }
        String opArg = argument.split(":")[1];

        switch (option) {
            case "f":
                Functions.listFunction(opArg);
                break;
            default:
                System.out.println("La opcion '" + option + "' no existe para list.");
        }

    }
}
