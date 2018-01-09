package creator.html.service;

import creator.html.model.HTMLCreator;
import creator.html.repository.HTMLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HTMLService {

    @Autowired
    HTMLRepository htmlRepository;

    public void saveHTML(HTMLCreator creator) {
        htmlRepository.saveAndFlush(creator);
    }
}
