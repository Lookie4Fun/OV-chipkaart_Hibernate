import java.sql.SQLException;
import java.util.List;

public interface AdresDAO {
    boolean save(Adres adres) throws SQLException;

    boolean update(Adres adres);

    boolean delete(Adres adres);

    Adres findByReiziger(Reiziger reiziger);

    List<Adres> findAll() throws SQLException;
}
