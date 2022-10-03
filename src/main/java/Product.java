import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany
    private List<OVChipkaart> ovChipkaarten= new ArrayList<>();

    public Product(int product_nummer, String naam, String beschrijving, int prijs) {
        this.product_nummer = product_nummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;

    }

    public Product() {

    }

    public List<OVChipkaart> getOvChipkaarten() {

        return ovChipkaarten;
    }


    public String toString(){
        return "Product #"+product_nummer+" -"+naam+" beschrijving: "+beschrijving+" €"+prijs+"| lijst van OVChipkaarten met dit product: ";
    }
}
