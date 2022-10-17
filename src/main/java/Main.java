import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.sql.SQLException;
import java.util.List;

/**
 * Testklasse - deze klasse test alle andere klassen in deze package.
 *
 * System.out.println() is alleen in deze klasse toegestaan (behalve voor exceptions).
 *
 * @author tijmen.muller@hu.nl
 */
public class Main {
    // Creëer een factory voor Hibernate sessions.
    private static final SessionFactory factory;


    static {
        try {
            // Create a Hibernate session factory
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.out.println(ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Retouneer een Hibernate session.
     *
     * @return Hibernate session
     * @throws HibernateException
     */
    private static Session getSession() throws HibernateException {
        return factory.openSession();
    }

    public static void main(String[] args) throws SQLException {
        OVChipkaartDAOHibernate ovChipkaartDAOHibernate = new OVChipkaartDAOHibernate(getSession());
        AdresDAOHibernate adresDAOPHibernate = new AdresDAOHibernate(getSession());
        ReizigerDAOHibernate reizigerDAOPHibernate = new ReizigerDAOHibernate(getSession(),adresDAOPHibernate,ovChipkaartDAOHibernate);
        ProductDAOHibernate productDAOHibernate = new ProductDAOHibernate(getSession());
        testDAOHibernate(ovChipkaartDAOHibernate, productDAOHibernate, reizigerDAOPHibernate, adresDAOPHibernate);
//        testFetchAll();

    }

    /**
     * P6. Haal alle (geannoteerde) entiteiten uit de database.
     */
    private static void testFetchAll() {
        Session session = getSession();
        try {
            Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                Query query = session.createQuery("from " + entityType.getName());

                System.out.println("[Test] Alle objecten van type " + entityType.getName() + " uit database:");
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
                System.out.println();
            }
        } finally {
            session.close();
        }
    }

    private static void testDAOHibernate(OVChipkaartDAOHibernate ovdao, ProductDAOHibernate pdao, ReizigerDAOHibernate rdao, AdresDAOHibernate adao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] reizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger reiziger : reizigers) {
            System.out.println(reiziger);
        }

        // Maak een reiziger aan en persisteer deze in de database
        Reiziger nieuwReiziger = new Reiziger(77,"N", "de", "Heij", java.sql.Date.valueOf("2001-01-07"));
        System.out.print("\n [Test] Eerst " + reizigers.size() + " reizigers, na reizigerDAO.save() ");
        rdao.save(nieuwReiziger);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Update een product in de database
        System.out.println("[Test] resultaat voor en na updaten reiziger met id 77");

        System.out.println("VOOR UPDATE");
        for (Reiziger reiziger: reizigers) {
            System.out.println(reiziger);
        }
        nieuwReiziger.setAchternaam("Steen");
        rdao.update(nieuwReiziger);
        System.out.println("\nNA UPDATE");
        reizigers = rdao.findAll();
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }

        System.out.println("\n---------- Test AdresDAO -------------");

        //Haal alle adressen op uit de database
        List<Adres> adressen = adao.findAll();
        System.out.println("[Test] AdresDAO.findAll() geeft de volgende adressen:");
        for (Adres a : adressen) {
            System.out.println(a);
        }

        // Maak een nieuwe adres aan en persisteer deze in de database
        Adres nieuwadres = new Adres(77,"1234XZ", "12", "huislaan", "Amsterdam", 77);
        System.out.print("\n[Test] Eerst " + adressen.size() + " adressen, na adresDAO.save() ");
        adao.save(nieuwadres);
        adressen = adao.findAll();
        System.out.println(adressen.size() + " adressen\n");

        // Update een adres in de database
        System.out.println("[Test] resultaat voor en na updaten van postcode op adres met id 77");
        System.out.println("VOOR UPDATE");
        for (Adres a : adressen) {
            System.out.println(a);
        }
        nieuwadres.setHuisnummer("12B");
        nieuwadres.setStraat("villalaan");
        adao.update(nieuwadres);
        System.out.println("\nNA UPDATE");
        adressen = adao.findAll();
        for (Adres a : adressen) {
            System.out.println(a);
        }

        // Delete een adres uit de database
        adao.delete(nieuwadres);
        System.out.println("\n[Test] resultaat na deleten van postcode op adres met id 77");
        adressen = adao.findAll();
        for (Adres a : adressen) {
            System.out.println(a);
        }

        System.out.println("\n---------- Test OVChipkaartDAO -------------");

        // Haal alle ovkaarten op uit de database
        List<OVChipkaart> kaarten = ovdao.findAll();
        System.out.println("[Test] OVChipkaart.findAll() geeft de volgende kaarten:");
        for (OVChipkaart k : kaarten) {
            System.out.println(k);
        }

        // Maak een OVChipkaart aan en persisteer deze in de database
        String kaartdatum = "2028-09-08";
        OVChipkaart ovChipkaart = new OVChipkaart(2403711,java.sql.Date.valueOf(kaartdatum),2,40,1);
        System.out.print("\n [Test] Eerst " + kaarten.size() + " kaarten, na OVChipkaartDAO.save() ");
        ovdao.save(ovChipkaart);
        kaarten = ovdao.findAll();
        System.out.println(kaarten.size() + " kaarten\n");

        // Update een ovkaart in de database
        System.out.println("[Test] resultaat voor en na updaten van ovkaart met kaartnummer 2403711");
        System.out.println("VOOR UPDATE");
        for (OVChipkaart k : kaarten) {
            System.out.println(k);
        }
        ovChipkaart.setKlasse(1);
        ovChipkaart.setSaldo(140);
        ovdao.update(ovChipkaart);
        System.out.println("\nNA UPDATE");
        kaarten = ovdao.findAll();
        for (OVChipkaart k : kaarten) {
            System.out.println(k);
        }

        // Delete een ovkaart uit de database
        ovdao.delete(ovChipkaart);
        System.out.println("\n[Test] resultaat na deleten van OVChipkaart met kaartnummer 2403711");
        kaarten = ovdao.findAll();
        for (OVChipkaart k : kaarten) {
            System.out.println(k);
        }

        System.out.println("\n---------- Test ProductDAO -------------");

        // Haal alle producten op uit de database
        List<Product> producten = pdao.findAll();
        System.out.println("[Test] Product.findAll() geeft de volgende producten:");
        for (Product p : producten) {
            System.out.println(p);
        }

        // Maak een product aan en persisteer deze in de database
        Product nieuwProduct = new Product(7,"Vertragings garantie", "5 dagen per week vertraging.", 20);
        System.out.print("\n [Test] Eerst " + producten.size() + " producten, na ProductDAO.save() ");
        pdao.save(nieuwProduct);
        producten = pdao.findAll();
        System.out.println(producten.size() + " producten\n");

        // Update een product in de database
        System.out.println("[Test] resultaat voor en na updaten van product met productnummer 7");
        nieuwProduct.setPrijs(50);
        System.out.println("VOOR UPDATE");
        for (Product p : producten) {
            System.out.println(p);
        }
        nieuwProduct.setBeschrijving("7 dagen Vertragings garantie");
        pdao.update(nieuwProduct);
        System.out.println("\nNA UPDATE");
        producten = pdao.findAll();
        for (Product p : producten) {
            System.out.println(p);
        }

        // Delete een product uit de database
        pdao.delete(nieuwProduct);
        System.out.println("\n[Test] resultaat na deleten van product met productnummer 7");
        producten = pdao.findAll();
        for (Product p : producten) {
            System.out.println(p);
        }


//         Delete een reiziger uit de database
        rdao.delete(nieuwReiziger);
        System.out.println("\n[Test] resultaat na deleten van reiziger op adres met id 77");
        reizigers = rdao.findAll();
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }


    }

}
