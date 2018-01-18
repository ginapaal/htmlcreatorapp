package creator.html.controller;


import creator.html.model.Offer;
import creator.html.model.HTMLUrl;
import creator.html.service.HTMLService;
import creator.html.service.ImageHandler;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AppController {

    @Autowired
    ImageHandler imageHandler;

    @Autowired
    HTMLService htmlService;

    Set<String> urlFromClipboard = new HashSet<>();

    int htmlId;

    @GetMapping(value = "/")
    public String render() {
        return "redirect:/login";
    }


    @GetMapping(value = "/index")
    public String renderForm() {
        return "uploadForm";
    }


    @PostMapping(value = "/index")
    public String gettingData(@RequestParam("title") String title,
                              @RequestParam("description") String description,
                              @RequestParam("price") String price,
                              @RequestParam("images") String pics,
                              Model model) {
//        System.out.println(title + description + price + pics);
        imageHandler.getImageURL(pics);
        Set<HTMLUrl> urls = imageHandler.getUrls();
        System.out.println(urls);
        Offer offer = new Offer(title, description, price, urls);
        htmlService.saveHTML(offer);
        htmlId=offer.getId();
        imageHandler.resetURLList();
        return "redirect:/ajanlat/" + htmlId;
    }

    @GetMapping(value = "/ajanlat")
    @ResponseBody
    public JSONObject idToSend() {
        return htmlService.offerIdToJson(htmlId);
    }


    @GetMapping(value = "/ajanlat/{html_id}")
    public String renderOffer(@PathVariable("html_id") int id, Model model) {
        Offer offer = htmlService.findHTMLById(id);
        model.addAttribute("offer", offer);
        return "response";
    }

}