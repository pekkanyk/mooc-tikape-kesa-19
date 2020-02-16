package sanakirja;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sanakirja {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void lisaa(String sana, String kaannos) {
        jdbcTemplate.update("INSERT INTO Sanasto (sana, kaannos)VALUES (?,?)", sana, kaannos);
    }

    public String kaanna(String sana) {
        List <String> kaannokset= jdbcTemplate.query("SELECT * FROM Sanasto WHERE sana = ?;", (rs,rowNum)-> rs.getString("kaannos"),sana);
        if (kaannokset.get(0)!=null) return kaannokset.get(0);
        else return null;
    }

    public int sanojenLukumaara() {
        return jdbcTemplate.queryForObject("SELECT COUNT (*) FROM sanasto;",Integer.class);
    }

    public List<String> kaannoksetListana() {
        List<String> lista = new ArrayList<>();
        lista = jdbcTemplate.query("SELECT sana, kaannos FROM Sanasto;", (rs,rowNum)-> rs.getString("sana")+" = "+rs.getString("kaannos"));

        return lista;
    }
    
    public List<Sanapari> sanaparit(){
        List<Sanapari> sanaparit= new ArrayList<>();
        jdbcTemplate.query("SELECT * FROM Sanasto;", (rs,rowNum)-> sanaparit.add(new Sanapari(rs.getString("sana"), rs.getString("kaannos"))));
        return sanaparit;
    }
}