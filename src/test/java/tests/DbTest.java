package tests;

import database.DBConnection;
import models.hh.Student;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

public class DbTest {

    @Test
    public void getBD() throws Exception {
        DBConnection dbConnection = new DBConnection();
        dbConnection.connectToDB();
        dbConnection.createStudent("Helllllllooooooo");
        ArrayList<Student> students = dbConnection.getData("students");
        assertEquals(students.get(0).getName(), "Dmitry Rak");
        dbConnection.close();
    }
}