package pl.kosan.tin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosan.tin.dao.CarDriverDao;
import pl.kosan.tin.model.CarDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StandardDriverService implements DriverService {


    @Autowired
    CarDriverDao driverDao;

    @Override
    public List<CarDriver> findAllCarDriver() {

        Optional<List<CarDriver>> listDriver = driverDao.findAllCarDriver();

        List<CarDriver> listCarDriver = new ArrayList<>();
        if (listDriver.isPresent()) {
            listCarDriver = listDriver.get();
        }
        return listCarDriver;
    }
}
