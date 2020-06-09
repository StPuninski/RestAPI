package tests;

import adapters.HHVacanciesAdapter;
import models.hh.Area;
import models.hh.Employer;
import models.hh.Salary;
import models.hh.Vacancy;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.testng.Assert.assertEquals;

public class HeadHunterAPITest extends BaseTest{

    @Test
    public void getVacancies() throws FileNotFoundException {
        Vacancy expectedQA = new Vacancy();
        expectedQA.setArea(new Area("Москва"));
        expectedQA.setSalary(new Salary(100000, 180000, "RUR"));
        expectedQA.setEmployer(new Employer("Aquiva Labs", true));
        expectedQA.setName("QA Engineer");

        expectedQA = gson.fromJson(new FileReader("src/test/resources/qaJob.json"), Vacancy.class);
        models.hh.Vacancies vacancies = new HHVacanciesAdapter().get("QA");
        assertEquals(vacancies.getItems().get(0), expectedQA);

        for (models.hh.Vacancy vacancy : vacancies.getItems()){
            System.out.println("Вакансия: " + vacancy.getName());
            if (vacancy.getSalary() != null) {
            System.out.println("Нач зп: " + vacancy.getSalary() );
        }}
    }
}
