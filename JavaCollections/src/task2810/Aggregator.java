package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.HHStrategy;
import com.javarush.task.task28.task2810.model.Model;
import com.javarush.task.task28.task2810.model.MoikrugStrategy;
import com.javarush.task.task28.task2810.model.Provider;

import com.javarush.task.task28.task2810.view.HtmlView;
import com.javarush.task.task28.task2810.view.View;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Aggregator {

    public static void main(String[] args) throws IOException {


        /*
        Provider provider = new Provider(new HHStrategy());
        Controller controller = new Controller(provider);
        controller.scan();
        */

        HtmlView view = new HtmlView();
        Provider provider = new Provider(new HHStrategy());
        Provider provider2 = new Provider(new MoikrugStrategy());
        Model model = new Model(view, provider, provider2);
        Controller controller = new Controller(model);
        view.setController(controller);
        view.userCitySelectEmulationMethod();



    }
}
