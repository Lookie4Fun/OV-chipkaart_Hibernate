import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @Column(name="product_nummer")
    private int product_nummer;

    @Column(name="naam")
    private String naam;

    @Column(name="beschrijving")
    private String beschrijving;

    @Column(name="prijs")
    private int prijs;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.MERGE,
            orphanRemoval = true
    )
    private List<OVChipkaartProduct> ovChipkaarten = new ArrayList<>();


    public Product(int product_nummer, String naam, String beschrijving, int prijs) {
        this.product_nummer = product_nummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    public Product() {

    }

    public List<OVChipkaartProduct> getOvChipkaarten() {

        return ovChipkaarten;

    }

    public int getProduct_nummer() {
        return product_nummer;
    }

    public String getNaam() {
        return naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public int getPrijs() {
        return prijs;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }

    public String toString(){
        return "Product #"+getProduct_nummer()+" -"+getNaam()+" beschrijving: "+getBeschrijving()+" €"+getPrijs()+"| lijst van OVChipkaarten met dit product: "+getOvChipkaarten().toString();
    }
}
