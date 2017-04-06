package com.teguh.jpa.crud.example.domain;

import com.teguh.jpa.crud.example.entity.Department;
import com.teguh.jpa.crud.example.repository.IDepartmentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Domain model untuk memproses table Department.
 */
@Repository
@Transactional
public class DepartmentModel {
    private final Logger logger = LogManager.getLogger(getClass());
    @Autowired
    private IDepartmentRepository repository;

    /**
     * Menambahkan record Department baru.
     *
     * @param entity Data department
     */
    public void create(Department entity) {
        Department result = repository.save(entity);
        logger.info("Creating Department entity with data: {}", result);
    }

    /**
     * Menghapus record Department dari database.
     *
     * @param id Department identity
     * @throws EntityNotFoundException
     */
    public void delete(Integer id) throws EntityNotFoundException {
        Department dept = repository.findOne(id);
        if (dept == null) {
            throw new EntityNotFoundException("entity.department.NotFound");
        }

        repository.delete(id);
        logger.info("A department entity have been deleted with data: {}", dept);
    }

    /**
     * Menghapus beberapa record Department dari database.
     *
     * @param ids Koleksi identity Department
     * @return Jumlah record yang berhasil dihapus
     */
    public int delete(Iterable<Integer> ids) {
        int count = 0;

        for (Integer Id : ids) {
            repository.delete(Id);
            count++;
        }
        logger.info("{} records of Department entity have been deleted.", count);

        return count;
    }

    /**
     * Menampilkan data informasi Department.
     *
     * @param id Department identity
     * @return Record department
     */
    public Department find(Integer id) {
        logger.info("Find Department entity with ID: {}.", id);
        return repository.findOne(id);
    }

    /**
     * Menampilkan daftar Department.
     *
     * @param pageable Paging and sorting method
     * @return The slice collection or data-paging collection of Person object.
     */
    @Transactional(readOnly = true)
    public Page<Department> listAll(Pageable pageable) {
        logger.info("Display list of Department entity.");
        return repository.listAllWithStats(pageable);
    }

    public void update(Department entity) throws EntityNotFoundException {
        if (entity == null || entity.getDeptId() == null) {
            throw new EntityNotFoundException("entity.department.NotFound");
        }
        Department record = repository.findOne(entity.getDeptId());
        if (record == null) {
            throw new EntityNotFoundException("entity.department.NotFound");
        }

        repository.save(entity);
        logger.info("Updating department: {}", entity);
    }

}
