package creator.app.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "html_id")
    private int id;

    private String title;

    private String description;

    private String price;

    @OneToMany(mappedBy = "offer", fetch = FetchType.EAGER)
    private List<HTMLUrl> url;

    public Offer() {
    }

    public Offer(String title, String description, String price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<HTMLUrl> getUrl() {
        return url;
    }

    public void setUrl(List<HTMLUrl> url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", url=" + url +
                '}';
    }
}
