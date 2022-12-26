package hillel.ua.service;

import hillel.ua.DataBase;
import hillel.ua.entity.Account;
import hillel.ua.entity.Clients;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private String QUERY_SAVE = "INSERT INTO clients (name, email, phone, about) VALUES (?,?,?,?)";
    private String QUERY_UPDATE = "UPDATE clients SET name = 'Kate' where id = 11";
    private String QUERY_DELETE = "DELETE FROM clients WHERE id = 11";
    private String QUERY_GET_ALL = "SELECT * FROM clients";
    private String QUERY_SEARCH_PHONE = "SELECT * FROM clients WHERE phone = '972344712'";
    private String QUERY_GET_CLIENTS = "SELECT * FROM clients INNER JOIN accounts a ON clients.id = a.client_id";
    private String QUERY_GET_ALL_TABLES = "select cl.name client_name, cl.email client_email, cl.phone client_phone, s.alias status_alias from clients cl inner join client_status cs on cl.id = cs.client_id inner join statuses s on cs.status_id = s.id where age > 18";


    public List<Clients> getAll() {
        List<Clients> client = new ArrayList<>();

        try (Connection connection = DataBase.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(QUERY_GET_ALL);
            while (resultSet.next()) {
                connection.setAutoCommit(false);
                Clients clients = new Clients();
                clients.setId(resultSet.getInt("id"));
                clients.setName(resultSet.getString("name"));
                clients.setEmail(resultSet.getString("email"));
                clients.setPhone(resultSet.getInt("phone"));
                clients.setAbout(resultSet.getString("about"));
                client.add(clients);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }


    public void save(Clients clients) {
        try (Connection connection = DataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SAVE)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, clients.getName());
            preparedStatement.setString(2, clients.getEmail());
            preparedStatement.setInt(3, clients.getPhone());
            preparedStatement.setString(4, clients.getAbout());
            preparedStatement.setString(5, clients.getAge());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Clients clients) {
        try (Connection connection = DataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE)) {
            connection.setAutoCommit(false);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Clients clients) {
        try (Connection connection = DataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE)) {
            connection.setAutoCommit(false);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Clients> searchPhone() {
        List<Clients> clients = new ArrayList<>();

        try (Connection connection = DataBase.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(QUERY_SEARCH_PHONE);
            while (resultSet.next()) {
                connection.setAutoCommit(false);
                Clients client = new Clients();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setEmail(resultSet.getString("email"));
                client.setPhone(resultSet.getInt("phone"));
                client.setAbout(resultSet.getString("about"));
                client.setAge(resultSet.getString("age"));
                clients.add(client);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public List<Clients> getClients() {
        List<Clients> clients = new ArrayList<>();

        try (Connection connection = DataBase.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(QUERY_GET_CLIENTS);
            while (resultSet.next()) {
                connection.setAutoCommit(false);
                Clients client = new Clients();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setEmail(resultSet.getString("email"));
                client.setPhone(resultSet.getInt("phone"));
                client.setAbout(resultSet.getString("about"));
                client.setAge(resultSet.getString("age"));
                clients.add(client);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

//    org.postgresql.util.PSQLException: Колонки id не найдено в этом ResultSet’’е.
//	at org.postgresql.jdbc.PgResultSet.findColumn(PgResultSet.java:2939)
//	at org.postgresql.jdbc.PgResultSet.getInt(PgResultSet.java:2817)
//	at hillel.ua.service.ClientService.allTable(ClientService.java:137)
//	at hillel.ua.Main.main(Main.java:22)
//    С этим не смог разобраться, с последним пунктом. В постгресе спокойно работает запрос, тут выдает такую ошибку.
    public List<Clients> allTable() {
        List<Clients> clients = new ArrayList<>();

        try (Connection connection = DataBase.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(QUERY_GET_ALL_TABLES);
            while (resultSet.next()) {
                connection.setAutoCommit(false);
                Clients client = new Clients();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setEmail(resultSet.getString("email"));
                client.setPhone(resultSet.getInt("phone"));
                client.setAbout(resultSet.getString("about"));
                client.setAge(resultSet.getString("age"));
                clients.add(client);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}
