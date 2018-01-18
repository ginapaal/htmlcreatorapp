package creator.html.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "datas")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "html_id")
    private int id;

    private String title;

    private String description;

    private String price;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "html_urls", joinColumns = @JoinColumn(name = "html_id"), inverseJoinColumns = @JoinColumn(name = "url_id"))
    private Set<HTMLUrl> url;

    public Offer() {
    }

    public Offer(String title, String description, String price, Set<HTMLUrl> url) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.url = url;
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

    public Set<HTMLUrl> getUrl() {
        return url;
    }

    public void setUrl(Set<HTMLUrl> url) {
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
