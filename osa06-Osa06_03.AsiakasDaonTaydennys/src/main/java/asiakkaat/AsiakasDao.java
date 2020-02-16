package asiakkaat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AsiakasDao implements Dao<Asiakas, Integer> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void create(Asiakas asiakas) throws SQLException {
        jdbcTemplate.update("INSERT INTO Asiakas"
                + " (nimi, puhelinnumero, katuosoite, postinumero, postitoimipaikka)"
                + " VALUES (?, ?, ?, ?, ?)",
                asiakas.getNimi(),
                asiakas.getPuhelinnumero(),
                asiakas.getKatuosoite(),
                asiakas.getPostinumero(),
                asiakas.getPostitoimipaikka());
    }

    @Override
    public Asiakas read(Integer key) throws SQLException {
        Asiakas asiakas = jdbcTemplate.queryForObject(
                "SELECT * FROM Asiakas WHERE id = ?",
                new BeanPropertyRowMapper<>(Asiakas.class),
                key);

        return asiakas;
    }

    @Override
    public Asiakas update(Asiakas asiakas) throws SQLException {
        jdbcTemplate.update("UPDATE Asiakas"
                + " SET nimi=?,"
                + " puhelinnumero=?,"
                + " katuosoite=?,"
                + " postinumero=?,"
                + " postitoimipaikka=?"
                + " WHERE ID=?;"
                , asiakas.getNimi(),asiakas.getPuhelinnumero(),asiakas.getKatuosoite(),
                asiakas.getPostinumero(),asiakas.getPostitoimipaikka(), asiakas.getId());
        
        return null;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        jdbcTemplate.update("DELETE FROM Asiakas WHERE ID=?", key);
    }

    @Override
    public List<Asiakas> list() throws SQLException {
        List<Asiakas> asiakkaat = new ArrayList<>();
        jdbcTemplate.query(
                "SELECT * FROM Asiakas;",
                (rs, rowNum)-> asiakkaat.add(new Asiakas(rs.getInt("id"), rs.getString("nimi"),
                rs.getString("puhelinnumero"), rs.getString("katuosoite"), rs.getInt("postinumero"),
                rs.getString("postitoimipaikka"))));
        if (asiakkaat.isEmpty()) return null;
        return asiakkaat;
    }
}
