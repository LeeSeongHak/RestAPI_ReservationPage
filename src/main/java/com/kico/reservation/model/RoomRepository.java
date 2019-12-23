package com.kico.reservation.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author イーソンハク
 */
@Repository
public interface RoomRepository extends JpaRepository<Roomdata, String> {
	Roomdata findRoomIdByRoomName(String room_id);
}