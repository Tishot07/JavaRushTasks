package modules.service;

import modules.comp.Comp;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompService {

    private List<Comp> compList = new ArrayList<Comp>();

    public CompService() {

        Comp c1 = new Comp();
        c1.setId(1);
        c1.setName("palata");
        c1.setCount(2);
        c1.setNecessary(true);
        compList.add(c1);

        Comp c2 = new Comp();
        c2.setId(2);
        c2.setName("prots");
        c2.setCount(5);
        c2.setNecessary(true);
        compList.add(c2);

        Comp c3 = new Comp();
        c3.setId(3);
        c3.setName("sound");
        c3.setCount(20);
        c3.setNecessary(false);
        compList.add(c3);

        Comp c4 = new Comp();
        c4.setId(4);
        c4.setName("monitor");
        c4.setCount(28);
        c4.setNecessary(false);
        compList.add(c4);
    }

    public List<Comp> getCompList() {
        return compList;
    }

    public void deleteComp(int id) {
        //сделать правильное удаление!
        compList.remove(id+1);
    }
}
