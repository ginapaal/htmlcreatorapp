package creator.app.controller;


import creator.app.model.Offer;
import creator.app.model.HTMLUrl;
import creator.app.service.HTMLService;
import creator.app.service.ImageHandler;
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
        imageHandler.getImageURL(pics);
        Set<HTMLUrl> urls = imageHandler.getUrls();
        System.out.println(urls);
        Offer offer = new Offer(title, description, price, urls);
        htmlService.saveHTML(offer);
        htmlId=offer.getId();
        imageHandler.resetURLList();
        return "redirect:" + htmlId + "/edit";
    }

    @GetMapping(value = "/ajanlat")
    @ResponseBody
    public JSONObject idToSend() {
        return htmlService.offerIdToJson(htmlId);
    }


    @GetMapping(value = "/{html_id}/edit")
    public String renderOffer(@PathVariable("html_id") int id, Model model) {
        Offer offer = htmlService.findHTMLById(id);
        model.addAttribute("offer", offer);
        return "edit";
    }

    @GetMapping(value="/ajanlat/{html_id}")
    public String renderOfferPage(@PathVariable("html_id") int id, Model model) {
        Offer offer = htmlService.findHTMLById(id);
        model.addAttribute("offer", offer);
        return "offer";
    }

}