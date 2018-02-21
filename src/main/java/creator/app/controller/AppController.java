package creator.app.controller;


import creator.app.model.HTMLUrl;
import creator.app.model.Offer;
import creator.app.service.HTMLService;
import creator.app.service.ImageHandler;
import creator.app.service.OfferService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppController {

    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    ImageHandler imageHandler;

    @Autowired
    OfferService offerService;

    @Autowired
    HTMLService htmlService;

    private int htmlId;

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
                              @RequestParam("images") String pics) {
        imageHandler.getImageURL(pics);
        List<HTMLUrl> urls = imageHandler.getUrls();
        Offer offer = new Offer(title, description, price);
        htmlService.saveUrl(urls);
        for (HTMLUrl url: urls) {
            url.setOffer(offer);
        }

        offer.setUrl(urls);
        offerService.saveOffer(offer);
        htmlId = offer.getId();
        imageHandler.resetURLList();
        return "redirect:" + htmlId + "/edit";
    }

    @GetMapping(value = "/ajanlat")
    @ResponseBody
    public JSONObject idToSend() {
        return offerService.offerIdToJson(htmlId);
    }


    @GetMapping(value = "/{html_id}/edit")
    public String renderOffer(@PathVariable("html_id") int id, Model model) {
        Offer offer = offerService.findOfferById(id);
        logger.info(offer);
        model.addAttribute("myOffer", offer);
        return "edit";
    }

    @GetMapping(value = "/ajanlat/{html_id}")
    public String renderOfferPage(@PathVariable("html_id") int id, Model model) {
        Offer offer = offerService.findOfferById(id);
        model.addAttribute("offer", offer);
        return "offer";
    }

    @PostMapping(value = "{html_id}/edit/delete")
    public String deleteCard(@PathVariable("html_id") int htmlId, @RequestParam("buttonId") int buttonId) {
        htmlService.deleteCard(buttonId);
        return "redirect:/" + htmlId + "/edit";
    }

    @GetMapping(value="/ajanlatok")
    public String allOffer(Model model) {
        model.addAttribute("offers", offerService.findAll());
        return "allOffers";
    }

}