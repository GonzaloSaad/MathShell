/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathshellapp.shell.Function;

import java.io.Serializable;

/**
 *
 * @author Gonzalo Saad
 */
public class SerializedFunction implements Serializable {
    private final String function;
    private final String name;
    
    public SerializedFunction(Function function){
        this.function = function.toString();
        this.name = function.getName();
    }
    
    
}
