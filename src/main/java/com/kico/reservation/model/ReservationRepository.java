package com.kico.reservation.model;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author イーソンハク
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservationdata, String> {
	int countDuplicateTime(String roomName, Timestamp startTime, Timestamp endTime);

	Reservationdata findReserveInfoByReserveId(Long reserve_id);

	List<Reservationdata> findReserveInfoByroomId(Long room_id, Timestamp today, Timestamp tomorrow);
}