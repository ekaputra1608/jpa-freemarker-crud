package com.teguh.jpa.crud.example.domain;

import com.teguh.jpa.crud.example.entity.Department;
import com.teguh.jpa.crud.example.entity.Person;
import com.teguh.jpa.crud.example.repository.IDepartmentRepository;
import com.teguh.jpa.crud.example.repository.IPersonRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Domain model untuk memproses table Person.
 */
@Repository
@Transactional(readOnly = false)
public class PersonModel {
    private final Logger logger = LogManager.getLogger(getClass());
    @Autowired
    private IPersonRepository repository;
    @Autowired
    private IDepartmentRepository departmentRepository;

    /**
     * Menambahkan record personil baru.
     *
     * @param entity Data personil
     */
    public void create(Person entity) {
        Person result = repository.save(entity);
        logger.info("Creating Person entity with data: {}", result);
    }

    /**
     * Menghapus record personil dari database.
     *
     * @param personId Personil identity
     * @throws EntityNotFoundException
     */
    public void delete(Long personId) throws EntityNotFoundException {
        Person person = repository.findOne(personId);
        if (person == null) {
            throw new EntityNotFoundException("entity.person.NotFound");
        }

        repository.delete(personId);
        logger.info("A Person entity have been deleted with data: {}", person);
    }

    /**
     * Menghapus beberapa record personil dari database.
     *
     * @param personIds Koleksi identity personil
     * @return Jumlah record personil yang berhasil dihapus.
     */
    public int delete(Iterable<Long> personIds) {
        int count = 0;

        for (Long personId : personIds) {
            repository.delete(personId);
            count++;
        }
        logger.info("{} records of Person entity have been deleted.", count);

        return count;
    }

    /**
     * Menampilkan data informasi personil.
     *
     * @param id Person identity
     * @return Record personil
     */
    @Transactional(readOnly = true)
    public Person find(Long id) {
        logger.info("Find Person entity with ID: {}.", id);
        return repository.findOne(id);
    }

    /**
     * Menampilkan daftar personil berdasarkan kriteria filter tertentu.
     *
     * @param deptId   Department identity
     * @param term     Kriteria pencarian berdasarkan nama lengkap personil
     * @param pageable Paging and sorting method
     * @return The slice collection or data-paging collection of Person object.
     */
    @Transactional(readOnly = true)
    public Page<Person> findAllByCriteria(Integer deptId, String term, Pageable pageable) {
        logger.info("Display list of Person entity with criteria: departement = {} and fullname = {}.", deptId, term);

        if (deptId == null && StringUtils.isBlank(term)) {
            return repository.findAll(pageable);
        } else if (deptId != null && StringUtils.isNotBlank(term)) {
            Department dept = departmentRepository.findOne(deptId);
            return repository.findByDepartementAndTerm(dept, "%" + term + "%", pageable);
        } else if (deptId != null && StringUtils.isBlank(term)) {
            Department dept = departmentRepository.findOne(deptId);
            return repository.findByDepartement(dept, pageable);
        } else {
            return repository.findByTerm("%" + term + "%", pageable);
        }
    }

    /**
     * Menampilkan daftar personil.
     *
     * @param pageable Paging and sorting method
     * @return The slice collection or data-paging collection of Person object.
     */
    @Transactional(readOnly = true)
    public Page<Person> listAll(Pageable pageable) {
        logger.info("Display list of Person entity.");
        return repository.findAll(pageable);
    }

    /**
     * Memperbarui record personil.
     *
     * @param entity Record personil yang telah diperbarui
     * @throws EntityNotFoundException
     */
    public void update(Person entity) throws EntityNotFoundException {
        if (entity == null || entity.getPersonId() == null) {
            throw new EntityNotFoundException("entity.person.NotFound");
        }

        Person record = repository.findOne(entity.getPersonId());
        if (record == null) {
            throw new EntityNotFoundException("entity.person.NotFound");
        }

        updateFields(entity, record);
        repository.save(record);
        logger.info("Updating person: {}", entity);
    }

    /**
     * Update persistence entity with transient entity.
     *
     * @param source transient entity
     * @param target persistence entity
     */
    private void updateFields(Person source, Person target) {
        target.setDepartment(source.getDepartment());
        target.setFullname(source.getFullname());
        target.setAddress(source.getAddress());
        target.setProvince(source.getProvince());
        target.setEmail(source.getEmail());
        target.setBirthDate(source.getBirthDate());
        target.setBirthPlace(source.getBirthPlace());
        target.setHomePhone(source.getHomePhone());
        target.setMobilePhone(source.getMobilePhone());
        target.setWorkPhone(source.getWorkPhone());
        target.setGender(source.getGender());
    }
}
