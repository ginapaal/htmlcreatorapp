package creator.html.controller;


import creator.html.model.HTMLCreator;
import creator.html.model.HTMLUrl;
import creator.html.service.HTMLService;
import creator.html.service.ImageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
public class PictureController {

    @Autowired
    ImageHandler imageHandler;

    @Autowired
    HTMLService htmlService;

    Set<String> urlFromClipboard = new HashSet<>();

    @GetMapping(value = "/")
    public String render() {
        return "redirect:/login";
    }


    @GetMapping(value = "/index")
    public String renderForm() {
        return "uploadForm";
    }


    @PostMapping(value = "/index")
    public String gettingData (@RequestParam("title") String title,
                               @RequestParam("description") String description,
                               @RequestParam("price") String price,
                               @RequestParam("images") String pics){
        System.out.println(title + description + price + pics);
        imageHandler.getImageURL(pics);
        Set<HTMLUrl> urls = imageHandler.getUrls();
        HTMLCreator creator= new HTMLCreator(title, description, price, urls);
        htmlService.saveHTML(creator);
        imageHandler.resetURLList();
        return "redirect:/data";
    }

    @GetMapping(value="/data")
    public String renderURLs(Model model) {
        model.addAttribute("urls", urlFromClipboard);
        return "response";
    }

}