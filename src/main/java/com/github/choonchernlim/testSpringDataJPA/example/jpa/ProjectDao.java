package com.github.choonchernlim.testSpringDataJPA.example.jpa;

import com.github.choonchernlim.testSpringDataJPA.entity.Project;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public final class ProjectDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void newProject(final Project project) {
        entityManager.persist(project);
    }

    public void deleteProject(final Project project) {
        entityManager.remove(project);
    }

    public Project getProjectByName(final String name) {
        return (Project) entityManager
                .createQuery("from Project p where p.name = :name")
                .setParameter("name", name)
                .getSingleResult();
    }
}
