package pl.kosan.tin.dao;

import pl.kosan.tin.dto.UserReservationRespDto;
import pl.kosan.tin.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ApplicationDao {

    Optional<List<UserReservationRespDto>> findReservationForUser(String email);



}
