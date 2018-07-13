package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.Model;
import com.javarush.task.task28.task2810.model.Provider;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//содержаться бизнес логика
public class Controller {

    //private Provider[] providers;
    private Model model;

    public Controller(Model model) {
        if (model == null)
            throw new IllegalArgumentException();
        this.model = model;
    }

    public void onCitySelect(String cityName) {
        model.selectCity(cityName);
    }

    /*
    public Controller(Provider... providers) {
        if (providers.length == 0) {
            throw new IllegalArgumentException();
        } else
            this.providers = providers;
    }
    */

    /*
    @Override
    public String toString() {
        return "Controller{" +
                "providers=" + Arrays.toString(providers) +
                '}';
    }
    */

    /*
    public void scan() {
        List<Vacancy> result = new ArrayList<>();
        int sum = 0;
        for (Provider temp:
             providers) {
            result.addAll(temp.getJavaVacancies(null));
            sum += temp.getJavaVacancies(null).size();
        }
        System.out.println(sum);
    }
    */
}
