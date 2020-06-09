package models.hh;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class Vacancy {
    @Expose
    String name;
    @Expose
    Area area;
    @Expose
    Salary salary;
    @Expose
    Employer employer;
}
