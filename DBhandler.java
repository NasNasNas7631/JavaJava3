package JavaJava3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBhandler {
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASSWORD = "123456789";
    private static String tableName = "progect12";

    public static String getTableName() {
        return tableName;
    }

    public static void setTableName(String newName) {
        tableName = newName;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void createTable() {
        String sql_ = "CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "speciality VARCHAR(255), " +
                "name_ VARCHAR(255), " +
                "group_ VARCHAR(255));";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql_);
            System.out.println("Таблица " + tableName + " успешно создана.");
        } catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы " + tableName);
            System.out.println(e);
        }
    }

    public static void saveString(String specialty, String name, String group) {

        String query = "INSERT INTO " + tableName + " (speciality, name_, group_) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);  ) {
            stmt.setString(1, specialty);
            stmt.setString(2, name);
            stmt.setString(3, group);
            stmt.executeUpdate();

            System.out.println("Результат операции сохранен в таблицу " + tableName);
        } catch (SQLException e) {
            System.out.println("Не удалось сохранить результат операции в таблицу " + tableName);
            System.out.println(e);
        }
    }

    // Метод для отображения всех таблиц
    public static void showAllTables() {
        // Получаем список всех таблиц
        String sqlTables = "SHOW TABLES;";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
        ) {
            ResultSet rsTables = stmt.executeQuery(sqlTables);
            while (rsTables.next()) {
                String name = rsTables.getString(1);
                System.out.println(name);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении списка таблиц");
        }
    }
    public static void DeleteStudentByID(int id){
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
        ) {
            String queryDelete = "DELETE from "+tableName+" where id ="+id+" ;";
            int rsTables = stmt.executeUpdate(queryDelete);
            System.out.printf("%d row(s) deleted\n", rsTables);


        } catch (SQLException e) {
            System.out.println("Ошибка при удалении данных из таблицы((");
        }
    }
    public static void getStrFromBD(int id){
        int ID = id;
        String name = "";
        String  specialty = "";
        String  group = "";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
        ) {
            String querySelect = "select * from "+tableName+" where id ="+id+" ;";
            ResultSet rsTables = stmt.executeQuery(querySelect);
            while (rsTables.next()){
                ID = rsTables.getInt(1);
                specialty = rsTables.getString(2);
                name = rsTables.getString(3);
                group = rsTables.getString(4);
                System.out.println("ID: "+ID+"\nSpecialty: "+specialty+"\nName: "+name+"\nGroup: "+group);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении данных из таблицы((");
        }
    }
}

