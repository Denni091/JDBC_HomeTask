package hillel.ua.service;

import hillel.ua.DataBase;
import hillel.ua.entity.Clients;
import hillel.ua.entity.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatusService {

    private String QUERY_GET_ALL = "SELECT * FROM statuses";
    private String QUERY_SAVE = "INSERT INTO statuses (alias, description) VALUES (?,?)";
    private String QUERY_UPDATE = "UPDATE statuses SET description = 'This people are main in the institution' where id = 5";
    private String QUERY_DELETE = "DELETE FROM statuses WHERE id = 5";
    private String QUERY_GET_ALL_TABLES = "select cl.name client_name, cl.email client_email, cl.phone client_phone, s.alias status_alias from clients cl inner join client_status cs on cl.id = cs.client_id inner join statuses s on cs.status_id = s.id;";
    public List<Status> getAll() {
        List<Status> status = new ArrayList<>();

        try (Connection connection = DataBase.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(QUERY_GET_ALL);
            while (resultSet.next()) {
                connection.setAutoCommit(false);
                Status statuses = new Status();
                statuses.setId(resultSet.getInt("id"));
                statuses.setAlias(resultSet.getString("alias"));
                statuses.setDescription(resultSet.getString("description"));
                status.add(statuses);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public void save(Status status) {
        try (Connection connection = DataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SAVE)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, status.getAlias());
            preparedStatement.setString(2, status.getDescription());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Status status) {
        try (Connection connection = DataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE)) {
            connection.setAutoCommit(false);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Status status) {
        try (Connection connection = DataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE)) {
            connection.setAutoCommit(false);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
