package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;

import java.io.IOException;
import java.util.List;

//Он будет отвечать за получение данных с сайта
public interface Strategy {
    List<Vacancy> getVacancies(String searchString);
}
