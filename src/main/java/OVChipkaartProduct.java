import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "ov_chipkaart_product")
public class OVChipkaartProduct implements Serializable {

    @Id
    @ManyToOne
    private OVChipkaart ovChipkaart;

    @Id
    @ManyToOne
    private Product product;



    public OVChipkaartProduct(OVChipkaart ovChipkaart, Product product ) {
        this.product = product;
        this.ovChipkaart = ovChipkaart;
    }

    public OVChipkaartProduct() {
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setOvChipkaart(OVChipkaart ovChipkaart) {
        this.ovChipkaart = ovChipkaart;
    }

    public Product getProduct() {
        return product;
    }

    public OVChipkaart getOvChipkaart() {
        return ovChipkaart;
    }

    public String toString(){
        return "kaart: "+getOvChipkaart()+" product: "+getProduct();
    }
}
