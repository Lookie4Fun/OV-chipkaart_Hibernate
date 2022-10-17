import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ReizigerDAOHibernate implements ReizigerDAO{

    private Session session;
    private AdresDAOHibernate adresDAOHibernate;
    private OVChipkaartDAOHibernate ovChipkaartDAOHibernate;
    private Transaction transaction;

    public ReizigerDAOHibernate(Session session, AdresDAOHibernate adresDAOHibernate, OVChipkaartDAOHibernate ovChipkaartDAOHibernate) {
        this.session = session;
        this.adresDAOHibernate = adresDAOHibernate;
        this.ovChipkaartDAOHibernate = ovChipkaartDAOHibernate;

    }

    @Override
    public boolean save(Reiziger reiziger){
        try {
            session.save(reiziger);
            adresDAOHibernate.save(reiziger.getAdres());
            for (OVChipkaart c : reiziger.getOVChipkaarten()) {
                ovChipkaartDAOHibernate.save(c);
            }
            transaction.commit();
            return true;
        }catch (Exception e){
            System.out.println(e);

            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try {
            adresDAOHibernate.update(reiziger.getAdres());
            for (OVChipkaart c : reiziger.getOVChipkaarten()) {
                ovChipkaartDAOHibernate.update(c);
            }
            session.update(reiziger);
            transaction.commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try {
            adresDAOHibernate.delete(reiziger.getAdres());
            for (OVChipkaart c : reiziger.getOVChipkaarten()) {
                ovChipkaartDAOHibernate.delete(c);
            }
            session.delete(reiziger);
            transaction.commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public List<Reiziger> findAll(){
        this.transaction = session.beginTransaction();
        List reizigers = session.createQuery("from Reiziger").list();
        return reizigers;

    }

    @Override
    public Reiziger findById(int id) {
        return null;
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        return null;
    }


}
