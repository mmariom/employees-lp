package com.mario.emplyees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyst implements  IEmployee{

    private String lastName;
    private String firstName;
    private LocalDate dob;
    private  int projectCount =0 ;




    private  final  String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
    private final Pattern peoplePat = Pattern.compile(peopleRegex);

    private final  String analystRegex = "\\w+=(?<projects>\\w+)";
    private final  Pattern analystPatter = Pattern.compile(analystRegex);


    private  final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");



    public Analyst(String personText) {
        Matcher peopleMat = peoplePat.matcher(personText);
        if(peopleMat.find()){
            this.lastName =  peopleMat.group("lastName");
            this.firstName =  peopleMat.group("firstName");
            this.dob = LocalDate.from(dateTimeFormatter.parse(peopleMat.group("dob")));
            Matcher analystMatcher = analystPatter.matcher(peopleMat.group("details"));
            if (analystMatcher.find()){
                this.projectCount = Integer.parseInt(analystMatcher.group("projects"));

            }

        }

    }

    public int getSalary(){
        // salary = 3000 +locpd * yoe *iq;
        return  2500 + projectCount * 2;
    }


    @Override
    public String toString() {
        return String.format("%s, %s : %s",lastName,firstName,moneyFormat.format(getSalary()));

    }
}
