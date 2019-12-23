package com.kico.reservation.service;

import java.sql.Timestamp;
import java.util.List;

import com.kico.reservation.model.Reservationdata;

/*
 * 開発者:イーソンハク
 * 使用目的：ReservationIOServiceのインターフェースとして使用するため
 * 使用方：implementsで宣言後使用
 */
public interface ReservationIOService {
	Reservationdata ReserveRoom(Reservationdata reservationAddData, Long roomId, Timestamp tsCurrentTime) throws Exception;

	Reservationdata GetReserveInfo(Long reserve_id);

	List<Reservationdata> GetRoomDayReserve(Long room_id, Timestamp tsToday, Timestamp tsTomorrow) throws Exception;
}