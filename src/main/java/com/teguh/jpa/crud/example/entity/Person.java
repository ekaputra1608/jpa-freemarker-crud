package com.teguh.jpa.crud.example.entity;

import com.teguh.jpa.crud.example.domain.types.Gender;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "person",
        indexes = {
                @Index(name = "person_x1", columnList = "fullname"),
                @Index(name = "person_x2", columnList = "dept_id"),
                @Index(name = "person_x3", columnList = "gender")
        })
@EntityListeners(AuditingEntityListener.class)
public class Person {
    private Long personId;
    private Department department;
    private String fullname;
    private String address;
    private String province;
    private String email;
    private String homePhone;
    private String workPhone;
    private String mobilePhone;
    private String birthPlace;
    private Date birthDate;
    private Gender gender;
    private Date tsCreated;
    private Date tsModified;
    private Integer version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!personId.equals(person.personId)) return false;
        if (!department.equals(person.department)) return false;
        if (!fullname.equals(person.fullname)) return false;
        if (!address.equals(person.address)) return false;
        if (!province.equals(person.province)) return false;
        if (!gender.equals(person.gender)) return false;
        if (email != null ? !email.equals(person.email) : person.email != null) return false;
        if (homePhone != null ? !homePhone.equals(person.homePhone) : person.homePhone != null) return false;
        if (workPhone != null ? !workPhone.equals(person.workPhone) : person.workPhone != null) return false;
        if (mobilePhone != null ? !mobilePhone.equals(person.mobilePhone) : person.mobilePhone != null) return false;
        if (birthPlace != null ? !birthPlace.equals(person.birthPlace) : person.birthPlace != null) return false;
        if (birthDate != null ? !birthDate.equals(person.birthDate) : person.birthDate != null) return false;
        if (tsCreated != null ? !tsCreated.equals(person.tsCreated) : person.tsCreated != null) return false;
        if (tsModified != null ? !tsModified.equals(person.tsModified) : person.tsModified != null) return false;

        return !(version != null ? !version.equals(person.version) : person.version != null);
    }

    @Basic
    @NotEmpty(message = "{validation.field.notEmpty}")
    @Column(columnDefinition = "text", nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NotNull(message = "{validation.field.notEmpty}")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Basic
    @Column(name = "birth_place", length = 250)
    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    @ManyToOne
    @NotNull(message = "{validation.field.notEmpty}")
    @JoinColumn(name = "dept_id", referencedColumnName = "dept_id", nullable = false)
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Basic
    @Email(message = "{validation.invalid.emailAddress}")
    @Column(name = "email", length = 200)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @NotEmpty(message = "{validation.field.notEmpty}")
    @Size(min = 5, max = 200, message = "{validation.field.size}")
    @Column(name = "fullname", length = 200, nullable = false)
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @NotNull(message = "{validation.field.notEmpty}")
    @Column(length = 1, nullable = false)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Basic
    @Size(max = 50, message = "{validation.field.maxSize}")
    @Column(name = "home_phone", length = 50)
    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    @Basic
    @Size(max = 50, message = "{validation.field.maxSize}")
    @Column(name = "mobile_phone", length = 50)
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Basic
    @NotEmpty(message = "{validation.field.notEmpty}")
    @Size(min = 5, max = 250, message = "{validation.field.size}")
    @Column(length = 250, nullable = false)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ts_created", columnDefinition = "datetime")
    public Date getTsCreated() {
        return tsCreated;
    }

    public void setTsCreated(Date tsCreated) {
        this.tsCreated = tsCreated;
    }

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ts_modified", columnDefinition = "datetime")
    public Date getTsModified() {
        return tsModified;
    }

    public void setTsModified(Date tsModified) {
        this.tsModified = tsModified;
    }

    @Version
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Basic
    @Size(max = 50, message = "{validation.field.maxSize}")
    @Column(name = "work_phone", length = 50)
    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    @Override
    public int hashCode() {
        int result = (personId != null ? personId.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (fullname != null ? fullname.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (birthPlace != null ? birthPlace.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (tsCreated != null ? tsCreated.hashCode() : 0);
        result = 31 * result + (tsModified != null ? tsModified.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", fullname='" + fullname + '\'' +
                ", address='" + address + '\'' +
                ", province='" + province + '\'' +
                ", email='" + email + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                '}';
    }
}
