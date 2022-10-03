import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reiziger")
public class Reiziger {
    @Id
    @Column(name="reiziger_id")
    private int id;

    @Column(name="voorletters")
    private String voorletters;

    @Column(name="tussenvoegsel")
    private String tussenvoegsel;

    @Column(name="achternaam")
    private String achternaam;

    @Column(name="geboortedatum")
    private Date geboortedatum;

    @OneToOne
    @JoinColumn(name="reiziger_id")
    private Adres adres;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "reiziger_id")
    private List<OVChipkaart> OVChipkaarten=new ArrayList<OVChipkaart>();


    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public Reiziger() {

    }

    public void addOVChipkaart(OVChipkaart kaart){
        OVChipkaarten.add(kaart);
    }
    public void removeOVChipkaart(OVChipkaart kaart){
        OVChipkaarten.remove(kaart);
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(LocalDate geboortedatum) {
        this.geboortedatum = Date.valueOf(geboortedatum);
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public List<OVChipkaart> getOVChipkaarten() {
        return OVChipkaarten;
    }

    public void setOVChipkaarten(List<OVChipkaart> OVChipkaarten) {
        this.OVChipkaarten = OVChipkaarten;
    }

    public String toString(){
        return "reiziger #"+id+": "+voorletters+". "+tussenvoegsel+" "+achternaam+", geboren op "+geboortedatum+", Adres"+getAdres()+" ovchipkaarten:"+getOVChipkaarten();
    }
}
