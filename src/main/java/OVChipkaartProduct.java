import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;


@Entity
@Table(name = "ov_chipkaart_product")
public class OVChipkaartProduct implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "kaart_nummer")
    private OVChipkaart ovChipkaart;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_nummer")
    private Product product;

    public OVChipkaartProduct(OVChipkaart ovChipkaart, Product product) {
    }
    public OVChipkaartProduct() {
    }



    public OVChipkaart getOvChipkaart() {
        return ovChipkaart;
    }

    public void setOvChipkaart(OVChipkaart ovChipkaart) {
        this.ovChipkaart = ovChipkaart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String toString(){
        return "ovChipkaart: "+getOvChipkaart().getKaart_nummer()+" product: "+getProduct().getProduct_nummer();
    }
}
