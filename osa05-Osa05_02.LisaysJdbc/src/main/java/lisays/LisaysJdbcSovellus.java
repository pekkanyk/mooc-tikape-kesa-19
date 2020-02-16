package lisays;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LisaysJdbcSovellus {

    public static void main(String[] args) throws Exception {
        Scanner lukija = new Scanner(System.in);
        System.out.println("Minkä niminen henkilö lisätään?");
        String syote = lukija.nextLine();
        // Toteuta uuden henkilön lisääminen tietokantatauluun Henkilo tänne
        Connection yhteys = DriverManager.getConnection("jdbc:h2:./henkilotietokanta", "sa", "");
        PreparedStatement statement = yhteys.prepareStatement("INSERT INTO Henkilo (nimi) VALUES (?)");
        statement.setString(1, syote);
        System.out.println("Lisatään "+syote);
        statement.executeUpdate();
        
        statement.close();
        yhteys.close();

    }

    private static void alustaTietokantaJaLuoNimia() {
        // mikäli poistat vahingossa tietokannan tai teet siihen muutoksia,
        // voit ajaa tämän metodin jolloin tietokantataulu luodaan uudestaan
        // ja siihen lisätään nimiä

        // palvelu http://listofrandomnames.com on tällaisissa varsin näppärä
        String nimet = "('Love'),"
                + "('Bruce'),"
                + "('Ray'),"
                + "('Fidelia'),"
                + "('Celia'),"
                + "('Mimi'),"
                + "('Monique'),"
                + "('Kristan'),"
                + "('Dane'),"
                + "('Anneliese'),"
                + "('Clemencia'),"
                + "('Jen'),"
                + "('Karen'),"
                + "('Tai'),"
                + "('Jesica'),"
                + "('Marya'),"
                + "('Kiesha'),"
                + "('Elidia'),"
                + "('Takisha'),"
                + "('Cleo')";
        try (Connection conn = DriverManager.getConnection("jdbc:h2:./henkilotietokanta", "sa", "")) {
            conn.prepareStatement("DROP TABLE Henkilo IF EXISTS;").executeUpdate();
            conn.prepareStatement("CREATE TABLE Henkilo(id serial, nimi varchar(255));").executeUpdate();
            conn.prepareStatement("INSERT INTO Henkilo (nimi) VALUES " + nimet + ";").executeLargeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LisaysJdbcSovellus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
