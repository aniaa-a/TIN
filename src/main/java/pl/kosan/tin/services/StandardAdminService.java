package pl.kosan.tin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kosan.tin.dao.AdminDao;
import pl.kosan.tin.dto.CarDriverReservationRespDto;


@Service
public class StandardAdminService implements AdminService {

    @Autowired
    AdminDao adminDao;

    @Override
    public void addCarDriverToReservation(CarDriverReservationRespDto carDriverReservationRespDto) {

        adminDao.addCarDriverToReservation(carDriverReservationRespDto);
    }


}
