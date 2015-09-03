package com.github.choonchernlim.testSpringDataJPA.example.jpa;

import com.github.choonchernlim.testSpringDataJPA.entity.ProjectEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public final class ProjectDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void newProject(final ProjectEntity project) {
        entityManager.persist(project);
    }

    public void deleteProject(final ProjectEntity project) {
        entityManager.remove(project);
    }

    public ProjectEntity getProjectByName(final String name) {
        return (ProjectEntity) entityManager
                .createQuery("from ProjectEntity p where p.name = :name")
                .setParameter("name", name)
                .getSingleResult();
    }
}
