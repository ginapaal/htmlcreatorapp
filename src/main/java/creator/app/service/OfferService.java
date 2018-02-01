package creator.app.service;

import creator.app.model.Offer;
import creator.app.repository.OfferRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    @Autowired
    OfferRepository offerRepository;

    public void saveOffer(Offer creator) {
        offerRepository.saveAndFlush(creator);
    }

    public Offer findOfferById(int id) {
        return offerRepository.findOne(id);
    }

    public JSONObject offerIdToJson(int offerId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", offerId);
        System.out.println(jsonObject);
        return jsonObject;
    }

    public List<Offer> findAll() {
        return offerRepository.findAll();
    }

}
