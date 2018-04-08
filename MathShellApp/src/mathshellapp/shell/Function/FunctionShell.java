/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathshellapp.shell.Function;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import mathshellapp.shell.Function.persistance.SerializedFunctionWritter;
import mathshellapp.shell.Functions;

/**
 *
 * @author Gonzalo
 */
public class FunctionShell {

    private final String SYM_PROMPT;
    private final String RGX_FUNCTION_CREATION = "[a-zA-Z][0-9]*\\(([a-z][0-9]*,)*[a-z][0-9]*\\)=.+";
    private final String RGX_FUNCTION_CALL = "[a-zA-Z][0-9]*\\((\\d+(\\.\\d+)?,)*\\d+(\\.\\d+)?\\)";
    private final String RGX_COMPOSED_FUNCTION_CALL = "[a-zA-Z][0-9]*\\(([a-zA-Z][0-9]*\\()+((\\d+(\\.\\d+)?,)*\\d+(\\.\\d+)?)+\\)+";
    private final String RGX_SAVE_FUNCTION = "save [a-zA-Z][0-9]* as [a-zA-Z0-9]+";
    private final String RGX_LOAD_FUNCTION = "load [a-zA-Z0-9]+ as [a-zA-Z][0-9]*";
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
            } else if (input.matches(RGX_COMPOSED_FUNCTION_CALL)) {
                //TODO!!!!            
            } else if (input.matches(RGX_SAVE_FUNCTION)) {
                System.out.println("save");
                operateSave(input);
            } else if (input.matches(RGX_LOAD_FUNCTION)) {
                //operateLoad(input);
                System.out.println("load");
            } else{
                System.out.println("NO");
            }

        } while (!input.equals(CMD_EXIT));

    }

    private void addFunction(String function) {
        try {
            Function f = new Function(function);
            String name = f.getName();
            functions.put(name, f);
            System.out.println("La funcion " + name + " fue agregada con exito.");
        } catch (IllegalArgumentException e){
            System.out.println("La expresion esta mal escrita.");
        } catch (Exception e){
            System.out.println("Hubo un error en agregar la funcion.");
        }

    }

    private void applyFunction(String function) {

        String name = function.split("\\(")[0];
        String arguments = function.substring(name.length() + 1, function.length() - 1);

        Function f = functions.get(name);

        if (f == null) {
            System.out.println("La funcion " + name + " no existe.");
            return;
        }

        try {
            System.out.println(f.apply(arguments));
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
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
                listfunCommand(args, argument);
                break;
        }
    }

    private void listfunCommand(boolean hasArgument, String argument) {
        for (String function : functions.keySet()) {
            System.out.println(function + ":\t" + functions.get(function).toString());
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

    private void operateSave(String input) {

        String auxString = input.split("save")[1];

        String function = auxString.split("as")[0].trim();
        String fileName = auxString.split("as")[1].trim();

        Function functionToSave = functions.get(function);

        if (functionToSave == null) {
            System.out.println("La funcion " + function + " no existe.");
            return;
        }

        SerializedFunctionWritter fw = new SerializedFunctionWritter();
        fw.save(fileName, functionToSave);

    }
}
