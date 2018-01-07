package creator.picture.controller;


import creator.picture.ImageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PictureController {

    @Autowired
    ImageHandler imageHandler;

    List<String> htmlData = new ArrayList<>();

    @GetMapping(value = "/")
    public String render() {
        return "textarea";
    }


    @PostMapping(value = "/")
    public String gettingData (@RequestParam("images") String imageContent){
        imageHandler.getImageURL(imageContent);
        List<String> urls = imageHandler.getUrls();
        htmlData.addAll(urls);
        imageHandler.resetURLList();
        return "redirect:/";
    }

    @GetMapping(value="/data")
    public String renderURLs(Model model) {
        model.addAttribute("data", htmlData);
        return "response";
    }

}