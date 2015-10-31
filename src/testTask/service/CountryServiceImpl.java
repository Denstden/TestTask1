package testTask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testTask.domain.Country;
import testTask.dao.CountryDao;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryDao countryDao;

    public CountryServiceImpl() {
    }

    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public CountryDao getCountryDao() {
        return countryDao;
    }

    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public Long createCountry(Country country) {
        return countryDao.create(country);
    }
}
