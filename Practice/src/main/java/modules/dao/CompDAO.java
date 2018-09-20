package modules.dao;

import javassist.NotFoundException;
import modules.entity.EntityComp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class CompDAO implements CompDAOInt {

/*
    private List<EntityComp> compList = new ArrayList<EntityComp>();

    public CompDAO() {

        EntityComp c1 = new EntityComp();
        c1.setId(1);
        c1.setName("palata");
        c1.setCount(2);
        c1.setNecessary(true);
        compList.add(c1);

        EntityComp c2 = new EntityComp();
        c2.setId(2);
        c2.setName("prots");
        c2.setCount(5);
        c2.setNecessary(true);
        compList.add(c2);

        EntityComp c3 = new EntityComp();
        c3.setId(3);
        c3.setName("sound");
        c3.setCount(20);
        c3.setNecessary(false);
        compList.add(c3);

        EntityComp c4 = new EntityComp();
        c4.setId(4);
        c4.setName("monitor");
        c4.setCount(28);
        c4.setNecessary(false);
        compList.add(c4);
    }

    public List<EntityComp> getCompList() {
        return compList;
    }

    public EntityComp findCompByName(String name) {
        Iterator<EntityComp> iter = compList.iterator();
        while(iter.hasNext()) {
            EntityComp comp = iter.next();
            if (comp.getName().equals(name)) {
                return comp;
            }
        }
        return null;
    }

    public void deleteComp(int id) {
        Iterator<EntityComp> iter = compList.iterator();
        while(iter.hasNext()) {
            EntityComp comp = iter.next();
            if (comp.getId() == id) {
                iter.remove();
            }
        }
    }

    public void updateComp(EntityComp comp) {
        deleteComp(comp.getId());
        addComp(comp);
    }

    public void addComp(EntityComp comp) {
        compList.add(comp);
    }

    public EntityComp getById(int id) {
        for (EntityComp c:
             compList) {
            if (c.getId() == id)
                return c;
        }
        return null;
    }

*/

    @Autowired
    private SessionFactory sessionFactory;

    public void addComp(EntityComp comp) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(comp);
    }

    public void updateComp(EntityComp comp) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(comp);
        System.out.println("update!");
    }

    public void deleteComp(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        EntityComp comp = (EntityComp) session.load(EntityComp.class, new Integer(id));
        if (comp != null) {
            session.delete(comp);
        }
    }

    public EntityComp getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        EntityComp comp = (EntityComp) session.load(EntityComp.class, new Integer(id));
        return comp;
    }

    public List<EntityComp> getCompList() {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<EntityComp>) session.createQuery("FROM " +EntityComp.class.getSimpleName()).list();
    }

    public EntityComp findCompByName(String name) {
        EntityComp comp = null;
        Session session = this.sessionFactory.getCurrentSession();
        try {
            List<EntityComp> compList = (List<EntityComp>)session.createQuery("from EntityComp  where name = :name").setString("name",name).list();

            if(compList.size()==0){
                throw new NotFoundException("not found name");
            }
            else {
                comp = compList.get(0);
            }

        }
        catch (NotFoundException e){
            e.printStackTrace();
        }

        return comp;
    }

}
