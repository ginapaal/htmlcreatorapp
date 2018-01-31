package creator.app.model;

import javax.persistence.*;


@Entity
public class HTMLUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "url_id")
    private int id;

    private String url;

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

    @Override
    public String toString() {
        return "HTMLUrl{" +
                ", url='" + url + '\'' +
                '}';
    }
}
