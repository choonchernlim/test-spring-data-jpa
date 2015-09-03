package com.github.choonchernlim.testSpringDataJPA.example.hibernate;

import com.github.choonchernlim.testSpringDataJPA.entity.ProjectEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class ProjectDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProjectDao(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void newProject(final ProjectEntity project) {
        sessionFactory.getCurrentSession().saveOrUpdate(project);
    }

    public void deleteProject(final ProjectEntity project) {
        sessionFactory.getCurrentSession().delete(project);
    }

    public ProjectEntity getProjectByName(final String name) {
        return (ProjectEntity) sessionFactory.getCurrentSession()
                .createQuery("from ProjectEntity p where p.name = :name")
                .setString("name", name)
                .uniqueResult();
    }
}
