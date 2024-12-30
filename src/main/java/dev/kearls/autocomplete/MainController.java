package dev.kearls.autocomplete;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    @GetMapping("/")
    public String index() {
        return "home";
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public String check(final @ModelAttribute("guess") String guess,
                            final Model model) {
        System.out.println("Guess is [" + guess + "]");
        model.addAttribute("guess", guess);
        return "check";
    }
}
