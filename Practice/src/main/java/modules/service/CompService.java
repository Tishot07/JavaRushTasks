package modules.service;

import modules.dao.CompDAOInt;
import modules.entity.EntityComp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class CompService implements CompServiceInt {

    @Autowired
    private CompDAOInt compDAO;

    @Transactional
    public List<EntityComp> getCompList() {
        return compDAO.getCompList();
    }
    @Transactional
    public void deleteComp(int id) {
        compDAO.deleteComp(id);
    }
    @Transactional
    public void addComp(EntityComp comp){
        compDAO.addComp(comp);
    }
    @Transactional
    public EntityComp getById(int id) {
        return compDAO.getById(id);
    }
    @Transactional
    public void update(EntityComp newComp) {
        compDAO.updateComp(newComp);
    }
    @Transactional
    public EntityComp findCompByName(String name) { return compDAO.findCompByName(name); }
}
