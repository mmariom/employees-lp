package com.mario.emplyees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager {


    private String lastName;
    private String firstName;
    private LocalDate dob;
    private  int orgSize =0 ;
    private  int de =0;



    private  final  String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
    private final Pattern peoplePat = Pattern.compile(peopleRegex);

    private  final  String managerRegex = "\\w+=(?<orgSize>\\w+),\\w+=(?<dr>\\w+)";
    private  final  Pattern managerPatter = Pattern.compile(managerRegex);


    private  final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");

    public Manager(String personText) {
        Matcher peopleMat = peoplePat.matcher(personText);
        if(peopleMat.find()){
            this.lastName =  peopleMat.group("lastName");
            this.firstName =  peopleMat.group("firstName");
            this.dob = LocalDate.from(dateTimeFormatter.parse(peopleMat.group("dob")));
            Matcher managerMatcher = managerPatter.matcher(peopleMat.group("details"));
            if (managerMatcher.find()){
                this.de = Integer.parseInt(managerMatcher.group("dr"));
                this.orgSize = Integer.parseInt(managerMatcher.group("orgSize"));

            }

        }

    }

    public int getSalary() {
        return 3500 + orgSize * de;
    }

    @Override
    public String toString() {
        return String.format("%s, %s : %s , orgsize : %s",lastName,firstName,moneyFormat.format(getSalary()),orgSize);

    }
}
