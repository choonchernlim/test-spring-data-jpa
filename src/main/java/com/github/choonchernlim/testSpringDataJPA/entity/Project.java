package com.github.choonchernlim.testSpringDataJPA.entity;

import com.google.common.base.Objects;
import org.joda.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "projectId")
    private Long id;

    @Column
    private String name;

    @Column
    private Integer totalUsers;

    @Column
    private LocalDate created;

    private Project() {
    }

    public Project(final String name, final Integer totalUsers, final LocalDate created) {
        this.name = name;
        this.totalUsers = totalUsers;
        this.created = created;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Project other = (Project) o;

        return Objects.equal(getName(), other.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(final LocalDate created) {
        this.created = created;
    }

    public Integer getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(final Integer totalUsers) {
        this.totalUsers = totalUsers;
    }
}
