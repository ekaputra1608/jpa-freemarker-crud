package com.teguh.jpa.crud.example.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "department", indexes = {@Index(name = "department_x1", columnList = "dept_name", unique = true)})
public class Department {
    private Integer deptId;
    private String deptName;
    private String description;
    private Long numberOfPerson;
    private Collection<Person> persons;

    /**
     * Class default constructor
     */
    public Department() {
    }

    /**
     * Class constructor.
     *
     * @param deptId         Department identity
     * @param deptName       Department name
     * @param description    Description of department
     * @param numberOfPerson The number of employee within a department
     */
    public Department(Integer deptId, String deptName, String description, Long numberOfPerson) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.description = description;
        this.numberOfPerson = numberOfPerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (!deptId.equals(that.deptId)) return false;
        if (!deptName.equals(that.deptName)) return false;
        return !(description != null ? !description.equals(that.description) : that.description != null);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer depId) {
        this.deptId = depId;
    }

    @Basic
    @NotEmpty(message = "{validation.field.notEmpty}")
    @Size(min = 3, max = 200, message = "{validation.field.size}")
    @Column(name = "dept_name", length = 200, nullable = false)
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Basic
    @Column(name = "description", columnDefinition = "text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Transient
    public Long getNumberOfPerson() {
        return numberOfPerson;
    }

    public void setNumberOfPerson(Long numberOfPerson) {
        this.numberOfPerson = numberOfPerson;
    }

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, targetEntity = Person.class)
    public Collection<Person> getPersons() {
        return persons;
    }

    public void setPersons(Collection<Person> persons) {
        this.persons = persons;
    }

    @Override
    public int hashCode() {
        int result = (deptId != null ? deptId.hashCode() : 0);
        result = 31 * result + (deptName != null ? deptName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Department{" +
                "depId=" + deptId +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
