/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathshellapp.shell.Function.persistance;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import mathshellapp.shell.Function.Function;

/**
 *
 * @author Gonzalo Saad
 */
public class SerializedFunctionWritter {

    public SerializedFunctionWritter() {
    }

    public void save(String nameToSave, Function functionToSave) {

        String arch = nameToSave + ".f";
        System.out.println("created");
        File file = new File(arch);

        if (file.exists()) {
            System.out.print("El archivo ya existe. Quiere reescribir? Si (s) No (n):");
            Scanner sc = new Scanner(System.in);
            String response = sc.nextLine();
            if ("s".equals(response)) {

                int i = 1;
                do {
                    arch = nameToSave + i + ".f";
                    file = new File(arch);
                    i++;
                } while (file.exists());
            
            } else {
                System.out.println("Guardado cancelado.");
                return;
            }
        }

        try (FileOutputStream ostream = new FileOutputStream(file)) {

            ObjectOutputStream p = new ObjectOutputStream(ostream);
            p.writeObject(functionToSave.getSerializedFunction());
            p.flush();
            System.out.println("Se guardo exitosamente.");

        } catch (Exception e) {
            System.out.println("Hubo un error.");
        }

        
    }

}
