package uz.pdp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home1(Model model) {
//        List<String> list = List.of("Muhammadkomil", "Bekzod", "Imron");
        List<Book> list = List.of(new Book("Otkan kunlar",12),new Book("Otkan kunlar",12));
        model.addAttribute("mess", "<i>Muhammadkomil</i>");
        model.addAttribute("list", list);
        return "home";
    }


    @GetMapping("/test")
    public String test(){
        return "test";
    }


    public record Book(
            String name,
            Integer page
    ){

    }
}
