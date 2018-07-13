package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {

    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";

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
            Elements vacancies = doc.getElementsByClass("job");

            if (vacancies.size()==0) break;
            for (Element element: vacancies)
            {
                if (element != null)
                {
                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(element.getElementsByAttributeValue("class", "title").text());
                    vacancy.setCompanyName(element.getElementsByAttributeValue("class", "company_name").text());
                    vacancy.setSiteName(URL_FORMAT);
                    vacancy.setUrl("https://moikrug.ru" + element.select("a[class=job_icon]").attr("href"));
                    String salary = element.getElementsByAttributeValue("class", "salary").text();
                    String city = element.getElementsByAttributeValue("class", "location").text();
                    vacancy.setSalary(salary.length()==0 ? "" : salary);
                    vacancy.setCity(city.length()==0 ? "" : city);
                    Vacancies.add(vacancy);
                }
            }
            page++;
        }
        return Vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        String str = String.format(URL_FORMAT, searchString, page);
        return Jsoup.connect(str).userAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64;rv:60.0) Gecko/20100101 Firefox/60.0").referrer("").get();
    }
}
