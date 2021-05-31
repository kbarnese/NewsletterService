package com.kbarnese.Newsletter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ThymeUserController {
    @Autowired
    private UserRepository userRepository;



    @GetMapping("/subscribe")
    public String getUser(Model model) {
        model.addAttribute("user", new User()); // New Book to add
        //model.addAttribute("books", bookRepository.findAll());
        return "/users";
    }

    @PostMapping("/subscribe")
    public String subscribe(@ModelAttribute("user") User user){

        if (userRepository.existsUserByEmail(user.getEmail())){
            User user1 = userRepository.findByEmail(user.getEmail());
            user1.setActive(true);
            user1.setFirstName(user.getFirstName());
            user1.setLastName(user.getLastName());
            userRepository.save(user1);

        }
        else{

            user.setActive(true);
            userRepository.save(user);
        }

        return "/thankyou";

    }

    @PostMapping("/unsubscribe")
    public String unsubscribe(@ModelAttribute("user") User user){

        if(userRepository.existsUserByEmail(user.getEmail())) {
            User user1 = userRepository.findByEmail(user.getEmail());
            user1.setActive(false);
            userRepository.save(user1);
            return "/thankyouUnsub";
        }
        else return "/no_unsubscribe";
    }

    @GetMapping("/unsubscribe")
    public String removeUser(Model model) {
        model.addAttribute("user", new User()); // New Book to add
        //model.addAttribute("books", bookRepository.findAll());
        return "/removeUser";
    }
}
