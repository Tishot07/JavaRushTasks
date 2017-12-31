package com.javarush.task.task19.task1905;

import java.util.HashMap;
import java.util.Map;

/* 
Закрепляем адаптер
*/

public class Solution {
    public static Map<String,String> countries = new HashMap<String,String>();

    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {

    }

    public static class DataAdapter implements RowItem {

        private Customer customer;
        private Contact contact;

        public DataAdapter(Customer customer, Contact contact) {
            this.customer = customer;
            this.contact = contact;
        }

        @Override
        public String getCountryCode() {
            String countryCode;
            for (Map.Entry<String, String> e : countries.entrySet()){
                if (e.getValue().equals(customer.getCountryName())) {
                    countryCode = e.getKey();
                    return countryCode;
                }
            }
            return countryCode = "";
        }

        @Override
        public String getCompany() {
            return customer.getCompanyName();
        }

        @Override
        public String getContactFirstName() {
            String[] firstName = contact.getName().split(", ");
            return firstName[1].trim();
        }

        @Override
        public String getContactLastName() {
            String[] lastName = contact.getName().split(", ");
            return lastName[0].trim();
        }

        @Override
        public String getDialString() {
            String dialString = contact.getPhoneNumber();
            dialString = dialString.replaceFirst("\\(", "");
            dialString = dialString.replaceFirst("\\)", "");
            dialString = dialString.replaceAll("-", "");
            return "callto://" + dialString.trim();
        }
    }

    public static interface RowItem {
        String getCountryCode();        //example UA
        String getCompany();            //example JavaRush Ltd.
        String getContactFirstName();   //example Ivan
        String getContactLastName();    //example Ivanov
        String getDialString();         //example callto://+380501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.
        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan
        String getPhoneNumber();        //example +38(050)123-45-67
    }
}