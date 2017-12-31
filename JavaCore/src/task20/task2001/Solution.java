package com.javarush.task.task20.task2001;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Читаем и пишем в файл: Human
*/
public class Solution {
    public static void main(String[] args) {
        //исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {

            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home"), new Asset("car"));
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }


    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Human human = (Human) o;

            if (name != null ? !name.equals(human.name) : human.name != null) return false;
            return assets != null ? assets.equals(human.assets) : human.assets == null;

        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (assets != null ? assets.hashCode() : 0);
            return result;
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintWriter write = new PrintWriter(outputStream);

            String isName = name != null ? "y" : "n";
            write.println(isName);

            if (name != null)
                write.println(name);

            String isList = assets != null ? "y": "n";
            write.println(isList);

            if (assets != null) {
                for (Asset s:
                     assets) {
                    String asName = s.getName() != null ? "y" : "n";
                    write.println(asName);
                    if (s.getName() != null)
                        write.println(s.getName());
                    String isPrice = s.getPrice() != 0.0 ? "y" : "n";
                    write.println(isPrice);
                    if (s.getPrice() != 0.0) {
                        write.println(s.getPrice());
                    }
                }
            }
            write.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод

            BufferedReader read = new BufferedReader(new InputStreamReader(inputStream));

            String isName = read.readLine();

            if (isName.equals("y")) {
                name = read.readLine();
            }
            String isList = read.readLine();
            ArrayList<Asset> temp = new ArrayList<>();
            if (isList.equals("y")) {
                while (read.ready()) {
                    String asName = read.readLine();//4
                    if (asName.equals("y")) {
                        Asset t = new Asset(read.readLine());
                        String isPrice = read.readLine();
                        if (isPrice.equals("y")) {
                            t.setPrice(Double.parseDouble(read.readLine()));
                        }
                        temp.add(t);
                    }
                    assets.addAll(temp);
                }
            }

        }
    }
}
