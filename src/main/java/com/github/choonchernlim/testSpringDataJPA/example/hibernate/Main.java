package com.github.choonchernlim.testSpringDataJPA.example.hibernate;

import com.github.choonchernlim.testSpringDataJPA.entity.Project;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private final ProjectDao dao;

    @Autowired
    public Main(final ProjectDao dao) {
        this.dao = dao;
    }

    public static void main(String[] args) {
        final ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-hibernate.xml");
        final Main main = context.getBean(Main.class);
        main.run();
    }

    public void run() {
        final String projectName = "Project X";

        LOGGER.debug("Project name: {}", projectName);

        final Project newProject = new Project(projectName, 10, LocalDate.now());

        dao.newProject(newProject);

        final Project existingProject = dao.getProjectByName(projectName);

        LOGGER.debug("{}, created on {}, with {} users",
                     existingProject.getName(),
                     existingProject.getCreated(),
                     existingProject.getTotalUsers());

        dao.deleteProject(existingProject);

    }
}
