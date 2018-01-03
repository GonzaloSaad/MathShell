/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathshellapp.shell.Calculator;

import java.util.HashMap;
import java.util.Scanner;
import mathshellapp.shell.Functions;
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
    private final String CMD_FUNCTION_LIST = "list";
    private final String RGX_COMPLEX_COMMAND = "[a-z]+(-[a-z]:[a-z]+)?";

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
            } else if (input.matches(RGX_COMPLEX_COMMAND)) {
                operateCommand(input);
            } else {
                calculate(input);
            }
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
