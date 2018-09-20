package modules.service;

import modules.entity.EntityComp;

import java.util.List;

public interface CompServiceInt {

    List<EntityComp> getCompList();

    void deleteComp(int id);

    void addComp(EntityComp comp);

    EntityComp getById(int id);

    void update(EntityComp newComp);

    EntityComp findCompByName(String name);
}
