package creator.picture.controller;


import creator.picture.service.ImageHandler;
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

    List<String> urlFromClipboard = new ArrayList<>();

    @GetMapping(value = "/")
    public String render() {
        return "redirect:/login";
    }


    @GetMapping(value = "/index")
    public String renderForm() {
        return "uploadForm";
    }


    @PostMapping(value = "/index")
    public String gettingData (@RequestParam("images") String imageContent){
        imageHandler.getImageURL(imageContent);
        List<String> urls = imageHandler.getUrls();
        urlFromClipboard.addAll(urls);
        imageHandler.resetURLList();
        return "redirect:/login";
    }

    @GetMapping(value="/data")
    public String renderURLs(Model model) {
        model.addAttribute("urls", urlFromClipboard);
        return "response";
    }

}