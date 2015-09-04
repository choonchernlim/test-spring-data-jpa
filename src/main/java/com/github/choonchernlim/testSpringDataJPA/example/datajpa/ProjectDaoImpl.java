package com.github.choonchernlim.testSpringDataJPA.example.datajpa;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public final class ProjectDaoImpl implements ProjectDaoCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean isProjectStupid(final String projectName) {
        return entityManager.createQuery("from Project p where p.name = :name")
                       .setParameter("name", projectName)
                       .getSingleResult() != null;
    }
}
