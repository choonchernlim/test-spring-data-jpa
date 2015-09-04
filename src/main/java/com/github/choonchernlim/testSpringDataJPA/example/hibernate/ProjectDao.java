package com.github.choonchernlim.testSpringDataJPA.example.hibernate;

import com.github.choonchernlim.testSpringDataJPA.entity.Project;
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

    public void newProject(final Project project) {
        sessionFactory.getCurrentSession().saveOrUpdate(project);
    }

    public void deleteProject(final Project project) {
        sessionFactory.getCurrentSession().delete(project);
    }

    public Project getProjectByName(final String name) {
        return (Project) sessionFactory.getCurrentSession()
                .createQuery("from Project p where p.name = :name")
                .setString("name", name)
                .uniqueResult();
    }
}
