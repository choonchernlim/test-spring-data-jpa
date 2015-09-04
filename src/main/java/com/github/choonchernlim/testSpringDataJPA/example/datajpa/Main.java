package com.github.choonchernlim.testSpringDataJPA.example.datajpa;

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

    private final ProjectDao projectDao;

    @Autowired
    public Main(final ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public static void main(String[] args) {
        final ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-data-jpa.xml");
        final Main main = context.getBean(Main.class);
        main.run();
    }

    public void run() {
        projectDao.save(new Project("Google", 10, new LocalDate(2015, 12, 1)));
        projectDao.save(new Project("Apple", 20, new LocalDate(2015, 6, 1)));
        projectDao.save(new Project("Microsoft", 5, new LocalDate(2016, 1, 1)));
        projectDao.save(new Project("Amazon", 10, new LocalDate(2016, 12, 1)));

        final Project existingProject1 = projectDao.anotherWayToGetProjectByName("Google");

        LOGGER.debug("{}, created on {}, with {} users",
                     existingProject1.getName(),
                     existingProject1.getCreated(),
                     existingProject1.getTotalUsers());

        final Project existingProject2 = projectDao.findByName("Google");

        LOGGER.debug("{}, created on {}, with {} users",
                     existingProject2.getName(),
                     existingProject2.getCreated(),
                     existingProject2.getTotalUsers());

        LOGGER.debug("isProjectStupid: " + projectDao.isProjectStupid("Google"));

        final Project project = projectDao.findByTotalUsersAndCreated(10, new LocalDate(2015, 12, 1));
        LOGGER.debug("findByTotalUsersAndCreated:  " + project.getName());

        for (Project p : projectDao.findByTotalUsers(10)) {
            LOGGER.debug("findByTotalUsers:  " + p.getName());
        }

        for (Project p : projectDao.findByTotalUsersOrderByNameAsc(10)) {
            LOGGER.debug("findByTotalUsersOrderByNameAsc:  " + p.getName());
        }

        for (Project p : projectDao.findByCreatedAfter(new LocalDate(2015, 12, 1))) {
            LOGGER.debug("findByCreatedAfter:  " + p.getName());
        }

        for (Project p : projectDao.findByCreatedBetween(new LocalDate(2015, 12, 1), new LocalDate(2015, 12, 31))) {
            LOGGER.debug("findByCreatedBetween:  " + p.getName());
        }

        for (Project p : projectDao.findByNameStartingWith("A")) {
            LOGGER.debug("findByNameStartingWith:  " + p.getName());
        }

        for (Project p : projectDao.findByNameEndingWithIgnoreCase("N")) {
            LOGGER.debug("findByNameEndingWithIgnoreCase:  " + p.getName());
        }

        projectDao.delete(existingProject1);
    }
}
