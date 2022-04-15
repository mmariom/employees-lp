package com.mario.emplyees;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee implements  IEmployee {

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
            """;

        String regex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(people);


//        String progRegex = "\\w+=(?<locpd>\\w+),\\w+=(?<yoe>\\w+),\\w+=(?<iq>\\w+)";
//        Pattern codePattern = Pattern.compile(progRegex);
//
//
//        String managerRegex = "\\w+=(?<orgSize>\\w+),\\w+=(?<dr>\\w+)";
//        Pattern managerPatter = Pattern.compile(managerRegex);
//
//        String analystRegex = "\\w+=(?<projects>\\w+)";
//        Pattern analystPatter = Pattern.compile(analystRegex);
//
//
//
//        String ceoRegex = "\\w+=(?<stockPrice>\\w+)";
//        Pattern ceoPatter = Pattern.compile(ceoRegex);

        int totalSalaries = 0;

        while (mat.find()) {
            totalSalaries += switch (mat.group("role")){
                case "Programmer" -> {
                    Programmer programmer = new Programmer(mat.group());
                    System.out.println(programmer.toString());
                    yield programmer.getSalary();

                }
                case "Manager" -> {
                    Manager manager = new Manager(mat.group());
//                    int salary = 0 ;
//                    Matcher managerMatcher = managerPatter.matcher(mat.group("details"));
//
//                    if (managerMatcher.find()){
//
//                        int orgSize = Integer.parseInt(managerMatcher.group("orgSize"));
//                        int dr = Integer.parseInt(managerMatcher.group("dr"));
//
//                      //  System.out.printf("orgSize is is : %s , dr is : %s%n", orgSize, dr);
//                        salary = 3500 + orgSize * dr;
//                    }
//                    else {
//                        salary = 3500;
//                    }
//                    System.out.printf("%s %s %s%n" , mat.group("lastName"), mat.group("firstName"),NumberFormat.getCurrencyInstance().format(salary));
                    System.out.println(manager.toString());
                    yield manager.getSalary();
                }
                case "Analyst" -> {
                    Analyst analyst  = new Analyst(mat.group());
//                    int salary =0 ;
//                    Matcher analystMatcher = analystPatter.matcher(mat.group("details"));
//
//                    if (analystMatcher.find()){
//
//                        int projects =  Integer.parseInt(analystMatcher.group("projects"));
//                     //   System.out.printf("project count is  : %s%n", projects);
//                        salary = 2500 + projects * 2;
//                    }  else {
//                        salary = 2500;
//                    }
//                   System.out.printf("%s %s %s%n" , mat.group("lastName"), mat.group("firstName"),NumberFormat.getCurrencyInstance().format(salary));
                    System.out.println(analyst.toString());

                    yield analyst.getSalary();
                }

                case "CEO" -> {
                    CEO ceo = new CEO(mat.group());
//                    int salary = 0;
//                    Matcher ceoMatcher = ceoPatter.matcher(mat.group("details")) ;
//
//                    if (ceoMatcher.find()){
//                         int stockPrice = Integer.parseInt(ceoMatcher.group("stockPrice")) ;
//                         System.out.printf("stock price is  : %s%n",stockPrice );
//                         salary = 5000 * stockPrice;
//                    }    else{
//                        salary = 5000;
//                    }
//                   System.out.printf("%s %s %s%n" , mat.group("lastName"), mat.group("firstName"),NumberFormat.getCurrencyInstance().format(salary));
                    System.out.println(ceo.toString());

                    yield ceo.getSalary();
                }
                default -> 0;
            };
        }
        NumberFormat currencyInstance= NumberFormat.getCurrencyInstance();
        System.out.printf("the total payout is %s %n",currencyInstance.format(totalSalaries));


    }
}
