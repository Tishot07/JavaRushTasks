package modules.service;

import modules.comp.Comp;
import modules.dao.CompDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompService {

    @Autowired
    private CompDAO compDAO;

    public List<Comp> getCompList() {
        return compDAO.getCompList();
    }

    public void deleteComp(int id) {
        compDAO.deleteComp(id);
    }


    public void addComp(Comp comp){
        compDAO.addComp(comp);
    }
}
