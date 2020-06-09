package models.hh;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.util.List;

@Data
public class Vacancies {


    @Expose
    List<Vacancy> items;


}
