package modules.dao;

import modules.entity.EntityComp;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CompDAOInt {

    @Transactional
    void addComp(EntityComp comp);

    @Transactional
    void updateComp(EntityComp comp);

    @Transactional
    void deleteComp(int id);

    @Transactional
    EntityComp getById(int id);

    @Transactional
    List<EntityComp> getCompList();

    @Transactional
    EntityComp findCompByName(String name);
}
