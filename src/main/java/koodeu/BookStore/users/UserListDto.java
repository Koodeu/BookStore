package koodeu.BookStore.users;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserListDto {

        private String firstName;
        private String surname;
        private String city;
        private String pesel;

    public UserListDto(String firstName, String surname, String city, String pesel) {
        this.firstName = firstName;
        this.surname = surname;
        this.city = city;
        this.pesel = pesel;
    }
}
