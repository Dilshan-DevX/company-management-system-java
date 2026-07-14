package com.nex.cms.model;

public class User {

    public static String first_name;
    private static String last_name;
    private static String email;
    private static String employeeId;
    private static String employeedep;

    public User(String first_name, String last_name, String email, String employeeId, String employeedep) {

        User.first_name = first_name;
        User.last_name = last_name;
        User.email = email;
        User.employeeId = employeeId;
        User.employeedep = employeedep;

    }

    public static String getFirst_name() {
        return first_name;
    }

    public static void setFirst_name(String first_name) {
        User.first_name = first_name;
    }

    public static String getLast_name() {
        return last_name;
    }

    public static void setLast_name(String last_name) {
        User.last_name = last_name;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        User.email = email;
    }

    public static String getEmployeeId() {
        return employeeId;
    }

    public static void setEmployeeId(String employeeId) {
        User.employeeId = employeeId;
    }

    public static String getEmployeedep() {
        return employeedep;
    }

    public static void setEmployeedep(String employeedep) {
        User.employeedep = employeedep;
    }

}
