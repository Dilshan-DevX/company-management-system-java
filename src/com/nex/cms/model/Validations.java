
package com.nex.cms.model;

public class Validations {
    
    public boolean charLen(String input, int value) {
        return input.length() <= value;
    }

    public boolean confirmPassword(String pass, String confPass) {
        return !pass.equals(confPass) ;
    }    

    public boolean charLenEquals(String string, int value) {
        return string.length() != value;
    }

    public boolean validateMobile(String mobile) {
        return !mobile.matches("^07[01245678]{1}[0-9]{7}$");
    }

    public boolean validateEmail(String email) {
        return !email.matches("^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@[^-]" //email regex verification for a valid format
                + "[A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$");
    }

    public boolean validatePassword(String password) {
        return !password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    public boolean validateComboBox(String selection) {
        return selection.equals("Select");
    }
}
