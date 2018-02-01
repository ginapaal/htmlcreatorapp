package creator.app.service;

import creator.app.model.HTMLUrl;
import creator.app.repository.HTMLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HTMLService {

    @Autowired
    HTMLRepository htmlRepository;

    public HTMLUrl getHTMLURLbyId(int id) {
        return htmlRepository.findOne(id);
    }

    public void deleteCard(int id) {
        htmlRepository.delete(id);
    }

    public void saveUrl(List<HTMLUrl> urlList) {
        htmlRepository.save(urlList);
    }

}
