package models.hh;

import com.google.gson.annotations.Expose;

public class Salary {
    @Expose
    int from;
    @Expose
    int to;
    @Expose
    String currency;

    public Salary(int min, int max, String Cash) {
    }
}
