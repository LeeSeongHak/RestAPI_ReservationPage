package com.kico.reservation.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kico.reservation.model.Reservationdata;
import com.kico.reservation.model.Roomdata;
import com.kico.reservation.service.ReservationIOService;
import com.kico.reservation.service.RoomIOService;
import com.kico.reservation.service.TimeService;

@RestController
public class ReservationController {

	@Autowired
	private ReservationIOService reservationIoService;

	@Autowired
	private RoomIOService roomIoService;

	@Autowired
	private TimeService timeService;

	/**
	 * @author イーソンハク
	 * @param Roomdata roomAddData
	 * @return Roomdata roomData
	 */
	//会議室の追加
	@PostMapping(path = "/room/add")
	public Roomdata AddRoom(@RequestBody Roomdata roomAddData) throws Exception {
		System.out.println("aa");
		System.out.println(roomAddData.getRoom_name());

		Timestamp tsCurrentTime = timeService.CurrentTime();
		Roomdata roomData = roomIoService.AddRoom(roomAddData, tsCurrentTime);
		return roomData;
	}

	/**
	 * @author イーソンハク
	 * @return List<Roomdata> roomList
	 */
	//会議室のリスト取得
	@GetMapping(path = "/room/list")
	public List<Roomdata> GetRoomList() {
		List<Roomdata> roomList = roomIoService.GetRoomList();
		return roomList;
	}

	/**
	 * @author イーソンハク
	 * @param Reservationdata reservationAddData
	 * @return Reservationdata reserveData
	 */
	//会議室の予約
	@PostMapping(path = "/room/reserve")
	public Reservationdata ReserveRoom(@RequestBody Reservationdata reservationAddData) throws Exception {
		//時間確認
		timeService.timeComparison(reservationAddData.getStart_time(), reservationAddData.getEnd_time());
		Timestamp tsCurrentTime = timeService.CurrentTime();
		Long roomId = roomIoService.findRoomIdByRoomName(reservationAddData.getRoom_name());
		Reservationdata reserveData = reservationIoService.ReserveRoom(reservationAddData, roomId, tsCurrentTime);
		return reserveData;
	}

	/**
	 * @author イーソンハク
	 * @param Long reserve_id
	 * @return Reservationdata reserveData
	 */
	//会議室の予約情報の取得
	@GetMapping(path = "/room/reserve/{reserve_id}")
	public Reservationdata GetReserveInfo(@PathVariable Long reserve_id) {
		Reservationdata reserveData = reservationIoService.GetReserveInfo(reserve_id);
		return reserveData;
	}

	/**
	 * @author イーソンハク
	 * @param Long room_id
	 * @return List<Reservationdata> reserveDayList
	 * @throws Exception
	 */
	//会議室の当日予約情報の取得
	@GetMapping(path = "/room/reserve/day/{room_id}")
	public List<Reservationdata> GetRoomDayReserve(@PathVariable Long room_id) throws Exception {
		Timestamp tsToday = timeService.TodayDate();
		Timestamp tsTomorrow = timeService.TomorrowDate();
		List<Reservationdata> reserveDayList = reservationIoService.GetRoomDayReserve(room_id, tsToday, tsTomorrow);

		return reserveDayList;
	}
}