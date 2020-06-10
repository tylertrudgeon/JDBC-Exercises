import java.sql.*;

import com.mysql.cj.jdbc.Driver;

public class Exercise {
    public static void main(String[] args) {

        try {
            DriverManager.registerDriver(new Driver());
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/codeup_test_db?serverTimezone=UTC&useSSL=false",
                    "root",
                    "codeup"
            );

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM albums");

            while(rs.next()) {
                System.out.println(rs.getString("Artist"));
            }

            String query = "INSERT INTO albums (artist, name, release_date, genre, sales) VALUES('Metallica', 'Death Magnetic', 2008, 'Rock', 13.5)";
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            rs = statement.getGeneratedKeys();

            if(rs.next()) {
                System.out.println("New record inserted. The ID is: " + rs.getLong(1));
            }

            long idToDelete = 33;
            query = "DELETE FROM albums WHERE id =" + idToDelete;
            statement.execute(query);
            System.out.println("Deleted id number " + idToDelete);


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
