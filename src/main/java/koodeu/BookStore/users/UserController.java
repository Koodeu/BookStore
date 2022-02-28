package koodeu.BookStore.users;

import koodeu.BookStore.Countries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(path = "/register")
    public String displayForm(Model model) {
        model.addAttribute("countries", Countries.values());
        model.addAttribute("emptyObject", new UserRegistrationDto());
        return "registerPage";
    }

    @PostMapping(path = "/register")
    public String register(@ModelAttribute UserRegistrationDto dto, Model model) {
        UserListDto userListDto = userService.saveUser(dto);
        model.addAttribute("firstname", "Imię: " + userListDto.getFirstName());
        model.addAttribute("surname", "Nazwisko: " + userListDto.getSurname());
        model.addAttribute("city", "Miasto: " + userListDto.getCity());
        return "welcomePage";
    }


}
