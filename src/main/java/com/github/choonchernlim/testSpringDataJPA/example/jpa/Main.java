package com.github.choonchernlim.testSpringDataJPA.example.jpa;

import com.github.choonchernlim.testSpringDataJPA.entity.ProjectEntity;
import org.joda.time.LocalDateTime;
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
        final ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-jpa.xml");
        final Main main = context.getBean(Main.class);
        main.run();
    }

    public void run() {
        final String projectName = "TEST" + LocalDateTime.now();

        LOGGER.debug("project name: " + projectName);

        final ProjectEntity project = new ProjectEntity();
        project.setName(projectName);
        project.setCreated(LocalDateTime.now());

        dao.newProject(project);

        final ProjectEntity aProject = dao.getProjectByName(projectName);

        LOGGER.debug(aProject.getName() + " " + aProject.getCreated());

        dao.deleteProject(aProject);
    }
}
