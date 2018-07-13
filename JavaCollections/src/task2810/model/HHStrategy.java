package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Этот класс будет реализовывать конкретную стратегию работы с сайтом
public class HHStrategy implements Strategy {

    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {

        List<Vacancy> Vacancies = new ArrayList<>();
        int page = 0;
        Document doc = null;
        while(true)
        {
            try {
                doc = getDocument(searchString, page);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Elements vacancies = doc.getElementsByClass("vacancy-serp__vacancy");
            Elements vacancies = doc.select("[data-qa=vacancy-serp__vacancy]");

            if (vacancies.size()==0) break;
            for (Element element: vacancies)
            {
                if (element != null)
                {
                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(element.select("[data-qa=vacancy-serp__vacancy-title]").text());
                    vacancy.setCompanyName(element.select("[data-qa=vacancy-serp__vacancy-employer]").text());
                    vacancy.setSiteName("http://hh.ua");
                    vacancy.setUrl(element.select("[data-qa=vacancy-serp__vacancy-title]").attr("href"));
                    String salary = element.select("[data-qa=vacancy-serp__vacancy-compensation]").text();
                    String city = element.select("[data-qa=vacancy-serp__vacancy-address]").text();
                    vacancy.setSalary(salary.length()==0 ? "" : salary);
                    vacancy.setCity(city.length()==0 ? "" : city);
                    Vacancies.add(vacancy);
                }
            }
            page++;
        }
        return Vacancies;

        /*
        try {
            //Document doc = Jsoup.connect(URL_FORMAT).userAgent("Firefox").get();
            //Document doc = Jsoup.connect(URL_FORMAT).userAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64;rv:60.0) Gecko/20100101 Firefox/60.0").referrer("").get();
            //System.out.println(doc.title());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.EMPTY_LIST;

        */
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        String str = String.format(URL_FORMAT, searchString, page);
        return Jsoup.connect(str).userAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64;rv:60.0) Gecko/20100101 Firefox/60.0").referrer("").get();
    }
}
