import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ProductDAOHibernate implements ProductDAO{

    private Session session;
    private Transaction transaction;

    public ProductDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Product product) {

        try {
            session.save(product);
            transaction.commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean update(Product product) {
        try {
            try{
                Query query = session.createQuery("update OVChipkaartProduct set last_update = :last_update" +
                        " where product_nummer = :productnummer");
                query.setParameter("last_update", Date.valueOf(LocalDate.now()));
                query.setParameter("productnummer", product.getProduct_nummer());
                query.executeUpdate();
            }catch (Exception e){
                System.out.println(e);
            }
            session.update(product);
            transaction.commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }

    }

    @Override
    public boolean delete(Product product) {
        try {
            Query query = session.createQuery("delete from OVChipkaartProduct where product_nummer = :productnummer");
            query.setParameter("productnummer", product.getProduct_nummer());
            query.executeUpdate();
            session.delete(product);
            transaction.commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public List<Product> findAll(){
        this.transaction = session.beginTransaction();
        List producten = session.createQuery("from Product").list();
        return producten;
    }

    @Override
    public List<Product> findByOVChipkaart(OVChipkaart ovChipkaart) {
        return null;
    }
}
