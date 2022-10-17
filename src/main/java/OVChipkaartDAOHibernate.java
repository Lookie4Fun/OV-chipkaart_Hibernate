import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class OVChipkaartDAOHibernate implements OVChipkaartDAO{
    private Session session;
    private Transaction transaction;

    public OVChipkaartDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(OVChipkaart kaart){
        try {
            session.save(kaart);
            transaction.commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean update(OVChipkaart kaart) {
        try {
            session.update(kaart);
            transaction.commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean delete(OVChipkaart kaart) {
        try {
            Query query = session.createQuery("delete from OVChipkaartProduct where kaart_nummer = :kaartnummer");
            query.setParameter("kaartnummer", kaart.getKaart_nummer());
            query.executeUpdate();
            session.delete(kaart);
            transaction.commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public List<OVChipkaart> findAll(){
        this.transaction = session.beginTransaction();
        List kaarten = session.createQuery("from OVChipkaart").list();
        return kaarten;
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        return null;
    }
}
