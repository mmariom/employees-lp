package com.mario.emplyees;

public class Nobody  implements  IEmployee{

    @Override
    public int getSalary() {
        return 0;
    }

    @Override
    public String toString() {
        return "Nobody";
    }
}
