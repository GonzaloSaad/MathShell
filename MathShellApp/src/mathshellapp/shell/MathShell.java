/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathshellapp.shell;

import java.util.Scanner;
import mathshellapp.shell.Calculator.CalculatorShell;
import mathshellapp.shell.Functions.FunctionShell;

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
    private final String CMD_CALCULATOR = "calc";
    private final String CMD_FUNCTION = "func";

    /**
     * Makes the whole shell start running, shows a prompt and waits for
     * the user to input something.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        String input;
        String command;
        String arguments;
        System.out.println("############ MATHSHELL ############");
        do {
            System.out.print(SYM_PROMPT + " ");
            input = sc.nextLine().trim().replace(" ", "");

            switch (input) {
                case CMD_CALCULATOR:
                    runCalculatorShell();
                    break;
                case CMD_FUNCTION:
                    runFunctionShell();
                    break;
            }

        } while (!input.equals(CMD_EXIT));

    }

    private void runCalculatorShell() {
        CalculatorShell c = new CalculatorShell(SYM_PROMPT);
        c.run();
    }

    private void runFunctionShell() {
        FunctionShell f = new FunctionShell(SYM_PROMPT);
        f.run();
    }

}
