package com.mario.emplyees;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee {

    public static void main(String[] args) {
        String people = """
            Flinstone, Fred, 1/1/1900, Programmer, {locpc=2000,yoe=10,iq=140}
            Flinstone2, Fred2, 1/1/1900, Programmer, {locpc=1300,yoe=14,iq=100}
            Flinstone3, Fred3, 1/1/1900, Programmer, {locpc=2300,yoe=8,iq=105}
            Flinstone4, Fred4, 1/1/1900, Programmer, {locpc=1630,yoe=3,iq=115}
            Flinstone5, Fred5, 1/1/1900, Programmer, {locpc=5,yoe=10,iq=100}
            Rubble, Barney, 2/2/1905, Manager, {orgSize=300,de=10}
            Rubble2, Barney2, 2/2/1905, Manager, {orgSize=100,de=4}
            Rubble3, Barney3, 2/2/1905, Manager, {orgSize=200,de=2}
            Rubble4, Barney4, 2/2/1905, Manager, {orgSize=500,de=8}
            Rubble5, Barney5, 2/2/1905, Manager, {orgSize=175,de=20}
            Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=3}
            Flinstone2, Wilma2, 3/3/1910, Analyst, {projectCount=4}
            Flinstone3, Wilma3, 3/3/1910, Analyst, {projectCount=5}
            Flinstone4, Wilma4, 3/3/1910, Analyst, {projectCount=6}
            Flinstone5, Wilma5, 3/3/1910, Analyst, {projectCount=9}
            Rubble, Betty, 4/4/1915, CEO, {avgStockPrice=300}
            Rubble, Betty, 4/4/1915, CEOw, {avgStockPrice=300}
            """;

        String regex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(people);




        int totalSalaries = 0;
        IEmployee employee = null;
        while (mat.find()) {
           employee = switch (mat.group("role")){
                case "Programmer" -> new Programmer(mat.group());
                case "Manager" -> new Manager(mat.group());
                case "Analyst" ->  new Analyst(mat.group());
                case "CEO" ->  new CEO(mat.group());
                default -> new Nobody();
             };

            System.out.println(employee.toString());
            totalSalaries += employee.getSalary();
            };

        NumberFormat currencyInstance= NumberFormat.getCurrencyInstance();
        System.out.printf("the total payout is %s %n",currencyInstance.format(totalSalaries));
        }



    }

