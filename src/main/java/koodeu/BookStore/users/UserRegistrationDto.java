package koodeu.BookStore.users;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDto {

    private String firstName;
    private String surname;
    private String zipCode;
    private String city;
    private String country;
    private String street;
    private String birthDate;
    private String pesel;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean listEnabled;

}
