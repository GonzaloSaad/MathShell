/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathshellapp.shell.Function.persistance;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import mathshellapp.shell.Function.SerializedFunction;

/**
 *
 * @author Gonzalo Saad
 */
public class SerializedFunctionReader {
    
    public SerializedFunctionReader(){
        
    }
    
    public SerializedFunction read(String name){
        SerializedFunction sf;
        
        String arch = name + ".f";
        
        try {
            FileInputStream istream = new FileInputStream(arch);
            ObjectInputStream p = new ObjectInputStream(istream);

            sf = (SerializedFunction) p.readObject();

            p.close();
            istream.close();
        } catch (Exception e) {
            sf = null;            
        }

        return sf;
    }
}
