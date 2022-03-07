package classes;

public class UserEmailandPassword {

    String fullName,Email,Password;





    public UserEmailandPassword(String fullName, String email, String password) {
        this.fullName = fullName;
        Email = email;
        Password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }


}
