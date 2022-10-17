import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.*;


@Entity
@Table(name = "ov_chipkaart")
public class OVChipkaart implements Serializable {

    @Id
    @Column(name="kaart_nummer")
    private int kaart_nummer;

    @Column(name="geldig_tot")
    private Date geldig_tot;

    @Column(name="klasse")
    private int klasse;

    @Column(name="saldo")
    private int saldo;

    @Column(name="reiziger_id")
    private int reiziger_id;

    @OneToMany(
            mappedBy = "ovChipkaart",
            cascade = CascadeType.MERGE,
            orphanRemoval = true
    )
    private List<OVChipkaartProduct> producten = new ArrayList<>();

    public OVChipkaart(int kaart_nummer, Date geldig_tot, int klasse, int saldo, int reiziger_id){
        this.kaart_nummer = kaart_nummer;
        this.geldig_tot = geldig_tot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger_id = reiziger_id;

    }

    public OVChipkaart() {

    }

    public void addProduct(Product product) {
        OVChipkaartProduct ovChipkaartProduct = new OVChipkaartProduct(this, product);
        producten.add(ovChipkaartProduct);
        product.getOvChipkaarten().add(ovChipkaartProduct);
    }

    public void removeProduct(Product product) {
        OVChipkaartProduct ovChipkaartProduct = new OVChipkaartProduct(this, product);
        product.getOvChipkaarten().remove(ovChipkaartProduct);
        producten.remove(ovChipkaartProduct);
        ovChipkaartProduct.setOvChipkaart(null);
        ovChipkaartProduct.setProduct(null);
    }

    public List<OVChipkaartProduct> getProducten() {
        return producten;
    }

    public int getKaart_nummer() {
        return kaart_nummer;
    }

    public Date getGeldig_tot() {
        return geldig_tot;
    }

    public int getKlasse() {
        return klasse;
    }

    public int getSaldo() {
        return saldo;
    }

    public int getReiziger_id() {
        return reiziger_id;
    }

    public String toString(){
        return "kaart #"+getKaart_nummer()+" van reiziger: "+getReiziger_id()+": geldig tot "+getGeldig_tot()+", klasse:"+getKlasse()+", saldo: €"+getSaldo()+", producten"+ getProducten();
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
