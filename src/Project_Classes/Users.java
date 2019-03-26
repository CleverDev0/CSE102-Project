package Project_Classes;

import java.security.PublicKey;

public class Users {

    public String userId;
    public String Email;
    public String Name;
    public String Surname;
    public String Password;
    public String PhoneNumber;
    public String TCNumber;
    public String ManagerCode;
    public String ApartmentNumber;
    public boolean isAdmin; //TODO: Inferface olarak ayarlancak

























    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getTCNumber() {
        return TCNumber;
    }

    public void setTCNumber(String TCNumber) {
        this.TCNumber = TCNumber;
    }

    public String getManagerCode() {
        return ManagerCode;
    }

    public void setManagerCode(String managerCode) {
        ManagerCode = managerCode;
    }

    public String getApartmentNumber() {
        return ApartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        ApartmentNumber = apartmentNumber;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Users() {

    }
}
