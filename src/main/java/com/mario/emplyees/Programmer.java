package com.mario.emplyees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmer implements  IEmployee {

    private String lastName;
    private String firstName;
    private LocalDate dob;
    private  int linesOfCode =0 ;
    private  int yearsOfExp =0;
    private  int iq = 0;



    private  final  String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
    private final Pattern peoplePat = Pattern.compile(peopleRegex);

    private  final  String progRegex = "\\w+=(?<locpd>\\w+),\\w+=(?<yoe>\\w+),\\w+=(?<iq>\\w+)";
    private  final Pattern progPattern = Pattern.compile(progRegex);


    private  final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");



    public Programmer(String personText) {
        Matcher peopleMat = peoplePat.matcher(personText);
        if(peopleMat.find()){
           this.lastName =  peopleMat.group("lastName");
           this.firstName =  peopleMat.group("firstName");
          this.dob = LocalDate.from(dateTimeFormatter.parse(peopleMat.group("dob")));
          Matcher progMatcher = progPattern.matcher(peopleMat.group("details"));
          if (progMatcher.find()){
              this.linesOfCode = Integer.parseInt(progMatcher.group("locpd"));
              this.yearsOfExp = Integer.parseInt(progMatcher.group("yoe"));
              this.iq = Integer.parseInt(progMatcher.group("iq"));

          }

        }

    }

    public int getSalary(){
        // salary = 3000 +locpd * yoe *iq;
        return 3000 + linesOfCode * yearsOfExp *iq ;
    }




    @Override
    public String toString() {
        return String.format("%s, %s : %s , yox : %s",lastName,firstName,moneyFormat.format(getSalary()),yearsOfExp);

    }


}
