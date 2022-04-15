package com.mario.emplyees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CEO {
    private String lastName;
    private String firstName;
    private LocalDate dob;
    private  int avgStockPrice = 0 ;



    private  final  String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
    private final Pattern peoplePat = Pattern.compile(peopleRegex);


   private final String ceoRegex = "\\w+=(?<stockPrice>\\w+)";
    private  final Pattern ceoPatter = Pattern.compile(ceoRegex);


    private  final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");



    public CEO(String personText) {
        Matcher peopleMat = peoplePat.matcher(personText);
        if(peopleMat.find()){
            this.lastName =  peopleMat.group("lastName");
            this.firstName =  peopleMat.group("firstName");
            this.dob = LocalDate.from(dateTimeFormatter.parse(peopleMat.group("dob")));
            Matcher ceoMatcher = ceoPatter.matcher(peopleMat.group("details"));
            if (ceoMatcher.find()){
                this.avgStockPrice = Integer.parseInt(ceoMatcher.group("stockPrice"));


            }

        }

    }

    public int getSalary(){
        // salary = 3000 +locpd * yoe *iq;
        return 5000 * avgStockPrice;
    }


    @Override
    public String toString() {
        return String.format("%s, %s : %s, stock price : %s",lastName,firstName,moneyFormat.format(getSalary()),avgStockPrice);

    }
}
