package com.teguh.jpa.crud.example.domain;

import com.teguh.jpa.crud.example.entity.Person;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * DataBind untuk menampilkan data-grid dari table Person.
 */
public class EmployeeDataGrid extends AbstractDataGrid {
    private List<Person> entries;
    private Integer departmentId;
    private String term;

    /**
     * Default class constructor.
     */
    public EmployeeDataGrid() {
    }

    /**
     * Class constructor.
     */
    public EmployeeDataGrid(Page<Person> pages) {
        setPageable(pages);
    }

    /**
     * Menampilkan kriteria filter berdasarkan departemen.
     *
     * @return Department identity
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * Menentukan kriteria filter berdasarkan departemen.
     *
     * @param departmentId department identity
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * Menampilkan data employee dari buffer.
     *
     * @return Koleksi data employee
     */
    public List<Person> getEntries() {
        return entries;
    }

    /**
     * Menempatkan data employee pada buffer.
     *
     * @param entries Koleksi data employee
     */
    public void setEntries(List<Person> entries) {
        this.entries = entries;
    }

    /**
     * Menampilkan kriteria pencarian berdasarkan nama lengkap employee.
     *
     * @return Kriteria pencarian
     */
    public String getTerm() {
        return term;
    }

    /**
     * Menentukan kriteria pencarian berdasarkan nama lengkap employee.
     *
     * @param term Kriteria pencarian
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * Menempatkan data hasil query ke dalam datagrid buffer.
     *
     * @param pages Data hasil query
     * @return Fluent interface, returns itself.
     */
    public EmployeeDataGrid setPageable(Page<Person> pages) {
        setEntries(pages.getContent());
        setNumberOfItems(pages.getNumberOfElements());
        setPage(pages.getNumber() + 1);
        setTotalItems(pages.getTotalElements());
        setTotalPages(pages.getTotalPages());

        return this;
    }

}
