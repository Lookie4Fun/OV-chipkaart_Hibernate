
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany
    private List<Product> producten = new ArrayList<>();

    public OVChipkaart(int kaart_nummer, Date geldig_tot, int klasse, int saldo, int reiziger_id){
        this.kaart_nummer = kaart_nummer;
        this.geldig_tot = geldig_tot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger_id = reiziger_id;
    }

    public OVChipkaart() {
    }

    public List<Product> getProducten() {
        return producten;
    }

    public void addProduct(Product product){
        producten.add(product);
    }

    public void removeProduct(Product product){
        producten.remove(product);
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
        return " kaart #"+kaart_nummer+" van reiziger:"+ reiziger_id+": geldig tot "+geldig_tot+", klasse:"+klasse+", saldo: €"+saldo;
//                +", producten"+ producten;
    }
}
