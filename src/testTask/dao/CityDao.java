package testTask.dao;

import testTask.domain.City;

import java.util.List;

public interface CityDao {
    Long create(City country);
    City read(Long id);
    boolean update(City country);
    boolean delete(City country);
    List findAll();
}
