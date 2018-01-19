package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nameFile = reader.readLine();
        List<String> list = new ArrayList<>();
        reader.close();

        if (args[0].equals("-u")) {
            String id = args[1];
            if (id.length() > 8) id = id.substring(0, 8);

            StringBuffer nameProduct = new StringBuffer();
            for (int i = 2; i < args.length-2; i++) {
                nameProduct.append(args[i] + " ");
            }
            String product = nameProduct.toString().trim();
            if (product.length() > 30) {
                product = product.substring(0, 30);
            }

            String price = args[args.length-2];
            if (price.length() > 8) {
                price = price.substring(0, 8);
            }

            String quantity = args[args.length-1];
            if (quantity.length() > 4) {
                quantity = quantity.substring(0, 4);
            }

            BufferedReader readFile = new BufferedReader(new FileReader(nameFile));
            while (readFile.ready()) {
                list.add(String.format("%s%n", readFile.readLine()));
            }
            readFile.close();

            list.add(String.format("%-8s%-30s%-8s%-4s", id, product, price, quantity));
            BufferedWriter writeFile = new BufferedWriter(new FileWriter(nameFile));
            for (String l:
                 list) {
                writeFile.write(l);
            }
            writeFile.close();
        }
        else if (args[0].equals("-d")) {
            BufferedReader readFile = new BufferedReader(new FileReader(nameFile));
            while (readFile.ready()) {
                list.add(String.format("%s%n", readFile.readLine()));
            }
            readFile.close();
            Iterator<String> iter = list.iterator();
            while (iter.hasNext()) {
                String id = iter.next().substring(0, 8);
                if (id.endsWith(args[1])) {
                    iter.remove();
                }
            }
            readFile.close();
            BufferedWriter writeFile = new BufferedWriter(new FileWriter(nameFile));
            for (String l:
                    list) {
                writeFile.write(l);
            }
            writeFile.close();

        }

    }

}
