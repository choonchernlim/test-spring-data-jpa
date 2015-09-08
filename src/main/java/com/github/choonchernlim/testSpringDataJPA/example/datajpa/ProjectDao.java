package com.github.choonchernlim.testSpringDataJPA.example.datajpa;

import com.github.choonchernlim.testSpringDataJPA.entity.Project;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectDao extends JpaRepository<Project, Long>, ProjectDaoCustom {

    @Query("from Project p where p.name = :name")
    Project anotherWayToGetProjectByName(@Param("name") String name);

    Project findByName(String name);

    Project findByTotalUsersAndCreated(Integer age, LocalDate created);

    List<Project> findByTotalUsers(Integer age);

    List<Project> findByTotalUsersOrderByNameAsc(Integer age);

    List<Project> findByCreatedAfter(LocalDate startDate);

    List<Project> findByCreatedBetween(LocalDate startDate, LocalDate endDate);

    List<Project> findByNameStartingWith(String start);

    List<Project> findByNameEndingWithIgnoreCase(String end);

    List<Project> findByTotalUsers(Integer age, Sort sort);
}
