import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class AdresDAOHibernate implements AdresDAO{

    private Session session;
    private Transaction transaction;

    public AdresDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Adres adres){
        try {
            session.save(adres);
            transaction.commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean update(Adres adres) {
        try {
            session.update(adres);
            transaction.commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }


    @Override
    public boolean delete(Adres adres) {
        try {
            session.delete(adres);
            transaction.commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        return null;
    }

    @Override
    public List<Adres> findAll(){
        this.transaction = session.beginTransaction();
        List adressen = session.createQuery("from Adres").list();
        return adressen;

    }
}
