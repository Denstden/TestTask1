package testTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testTask.domain.City;
import testTask.dao.CityDao;

@Service
@Transactional
public class CityServiceImpl implements CityService{
    @Autowired
    private CityDao cityDao;

    public CityServiceImpl() {
    }

    public CityServiceImpl(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    public CityDao getCityDao() {
        return cityDao;
    }

    public void setCityDao(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public Long createCity(City city) {
        return cityDao.create(city);
    }
}
