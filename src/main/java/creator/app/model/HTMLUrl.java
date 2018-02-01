package creator.app.model;

import javax.persistence.*;


@Entity
public class HTMLUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "url_id")
    private int id;

    private String url;

    @ManyToOne
    private Offer offer;

    public HTMLUrl() {
    }

    public HTMLUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    @Override
    public String toString() {
        return "HTMLUrl{ id: " + getId() +
                ", url='" + url + '\'' +
                '}';
    }
}
