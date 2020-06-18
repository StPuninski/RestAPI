package database;

import models.hh.Student;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public void connectToDB(){
        try {
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/aqa4?" +
                            "user=root&password=admin");
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Student> getData(String tableName) {
        // Result set get the result of the SQL query
        ArrayList<Student> students = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("select * from " + tableName);
            while(resultSet.next()) {
                Student student = new Student(
                        resultSet.getString("id"),
                        resultSet.getString("name"));
                students.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return students;
    }

    public void createStudent(String name) {
        try {
            PreparedStatement preparedStatement =
                    connect.prepareStatement("INSERT INTO aqa4.students (name) VALUES(?)");
            preparedStatement.setString(1, name);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        }catch (Exception ignored) {
        }
    }

}