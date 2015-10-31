package testTask.dao;

import testTask.domain.Country;

import java.util.List;

public interface CountryDao {
    Long create(Country country);
    Country read(Long id);
    boolean update(Country country);
    boolean delete(Country country);
    List findAll();
}
