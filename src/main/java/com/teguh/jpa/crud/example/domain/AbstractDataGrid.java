package com.teguh.jpa.crud.example.domain;

import org.springframework.data.domain.Sort;

public abstract class AbstractDataGrid {
    public static final int PAGESIZE = 15;
    protected int numberOfItems;
    protected int page;
    protected String sortDir;
    protected String sortField;
    protected Sort sort;
    protected long totalItems;
    protected int totalPages;

    /**
     * Returns number of items in current page.
     */
    public int getNumberOfItems() {
        return numberOfItems;
    }

    /**
     * Sets number of items in current page.
     *
     * @param numberOfItems number of items
     * @return Fluent interface, returns itself.
     */
    public AbstractDataGrid setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
        return this;
    }

    /**
     * Returns the page number of current page.
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets the page number for current page.
     *
     * @param page page number
     * @return Fluent interface, returns itself.
     */
    public AbstractDataGrid setPage(int page) {
        this.page = page;
        return this;
    }

    /**
     * Returns maximum items within a page.
     */
    public int getPageSize() {
        return AbstractDataGrid.PAGESIZE;
    }

    /**
     * Returns the sorting method.
     */
    public Sort getSort() {
        return sort;
    }

    /**
     * Sets the sorting method.
     *
     * @param sort The sorting method.
     * @return Fluent interface, returns itself.
     */
    public AbstractDataGrid setSort(Sort sort) {
        this.sort = sort;
        return this;
    }

    /**
     * Get sorting method specification.
     *
     * @param defaultField the default sort field
     * @return The sorting method
     */
    public Sort getSortSpec(String defaultField) {
        if (sort != null) {
            return sort;
        }

        if (sortDir == null || sortDir.isEmpty()) {
            sortDir = "asc";
        }
        if (sortField == null || sortField.isEmpty()) {
            sortField = defaultField;
        }

        sort = new Sort(Sort.Direction.fromString(sortDir), sortField);
        return sort;
    }

    /**
     * Returns the sort direction.
     */
    public String getSortDir() {
        return sortDir;
    }

    /**
     * Sets the sort direction.
     *
     * @param sortdir sort direction
     * @return Fluent interface, returns itself.
     */
    public AbstractDataGrid setSortDir(String sortdir) {
        this.sortDir = sortdir;
        return this;
    }

    /**
     * Returns the field to sort.
     */
    public String getSortField() {
        return sortField;
    }

    /**
     * Sets the field to sort.
     *
     * @param sortfield The field to sort
     * @return Fluent interface, returns itself.
     */
    public AbstractDataGrid setSortField(String sortfield) {
        this.sortField = sortfield;
        return this;
    }

    /**
     * Gets the number of total items returns from a query resultset.
     */
    public long getTotalItems() {
        return totalItems;
    }

    /**
     * Sets the number of total items returns from a query resultset.
     *
     * @param totalItems Number of total items
     * @return Fluent interface, returns itself.
     */
    public AbstractDataGrid setTotalItems(long totalItems) {
        this.totalItems = totalItems;
        return this;
    }

    /**
     * Returns the number of total pages.
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * Sets the number of total pages.
     *
     * @param totalPages Number of total pages
     * @return Fluent interface, returns itself.
     */
    public AbstractDataGrid setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }
}
