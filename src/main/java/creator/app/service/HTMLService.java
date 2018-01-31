package creator.app.service;

import creator.app.model.Offer;
import creator.app.repository.HTMLRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HTMLService {

    @Autowired
    HTMLRepository htmlRepository;

    public void saveHTML(Offer creator) {
        htmlRepository.saveAndFlush(creator);
    }

    public Offer findHTMLById(int id) {
        return htmlRepository.findOne(id);
    }

    public JSONObject offerIdToJson(int offerId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", offerId);
        System.out.println(jsonObject);
        return jsonObject;
    }
}
