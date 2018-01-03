/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathshellapp.shell;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Gonzalo
 */
public class Functions {
    
    private static final HashMap<String, String> FUNCTION_LIST = buildFunctionList();
    
    


    public static HashMap<String,String> buildFunctionList() {
        HashMap<String, String> mp = new HashMap<>();
        mp.put("sin", "calcula el seno del argumento.");
        mp.put("cos", "calcula el coseno del argumento.");
        mp.put("tan", "calcula la tangente del argumento.");
        mp.put("cot", "calcula la cotagente del argumento.");
        mp.put("log", "calcula el logaritmo en base e (logaritmo neperiano) del argumento.");
        mp.put("log2", "calcula el logaritmo en base 2 del argumento.");
        mp.put("log10", "calcula el logaritmo en base 10 del argumento.");
        mp.put("log1p", "calcula el logatirmo en base e (logaritmo neperiano) del argumento sumado en 1.");
        mp.put("abs", "calcula el valor absoluto del argumento.");
        mp.put("acos", "calcula el arco coseno del argumento.");
        mp.put("asin", "calcula el arco seno del argumento.");
        mp.put("atan", "calcula el arco tangente del argumento.");
        mp.put("sinh", "calcula el seno hiperbolico del argumento.");
        mp.put("tanh", "calcula la tangente hiperbolica del argumento.");
        mp.put("cosh", "calcula el coseno hiperbolico del agumento.");
        mp.put("sqrt", "calcula la raiz cuadrada del argumento.");
        mp.put("cbrt", "calcula la raiz cubica del argumento.");
        mp.put("floor", "calcula el mayor entero, que es menor al argumento.");
        mp.put("ceil", "calcula el menor entero, que es mayor al arguemento.");
        mp.put("pow", "calcula el primer argumento elevado al segundo argumento.");
        mp.put("exp", "calcula el numero e, elevado al argumento. ");
        mp.put("expm1", "calcula el numero e, elevado al argumento, y a ello, le resta uno, es decir, la exponencial decrementada en uno. ");
        mp.put("signum", "calcula la funcion signo, retorna 1 si es positivo, -1 si es negativo.");
        return mp;
    }
    
    public static void listFunction() {
        for (String function : FUNCTION_LIST.keySet()) {
            showFunction(function, FUNCTION_LIST.get(function));
        }
    }
    
    public static void listFunction(String fun){
        if (!FUNCTION_LIST.containsKey(fun)){
            System.out.println("La funcion '"+ fun +"' no existe.");
            return;
        }
        showFunction(fun,FUNCTION_LIST.get(fun));
    }
    
    public static void showFunction(String fun,String description){
        System.out.println(fun + ":\t" + description);
    }
}
