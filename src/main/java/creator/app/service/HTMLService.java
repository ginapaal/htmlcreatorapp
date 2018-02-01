package creator.app.service;

import creator.app.model.HTMLUrl;
import creator.app.repository.HTMLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HTMLService {

    @Autowired
    HTMLRepository htmlRepository;

    public HTMLUrl getHTMLURLbyId(int id) {
        return htmlRepository.findOne(id);
    }

    public void deleteCard(int id) {
        HTMLUrl url = getHTMLURLbyId(id);
        htmlRepository.delete(url);
    }
}
