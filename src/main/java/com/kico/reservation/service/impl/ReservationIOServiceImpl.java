package com.kico.reservation.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kico.reservation.model.ReservationRepository;
import com.kico.reservation.model.Reservationdata;
import com.kico.reservation.service.ReservationIOService;

@Service
public class ReservationIOServiceImpl implements ReservationIOService {

	@Autowired
	ReservationRepository reservationRepository;

	private String blankSpace = "";

	/**
	 * @author イーソンハク
	 * @param Reservationdata reservationAddData, Long roomId, Timestamp
	 *                        tsCurrentTime
	 * @return Reservationdata reserveData
	 * @exception データ入力エラー
	 */
	// 会議室の予約
	@Override
	public Reservationdata ReserveRoom(Reservationdata reservationAddData, Long roomId, Timestamp tsCurrentTime)
			throws Exception {
		if (reservationAddData.getRoom_name().equals(blankSpace)
				|| reservationAddData.getStart_time().equals(blankSpace)
				|| reservationAddData.getEnd_time().equals(blankSpace)
				|| reservationAddData.getCreate_user().equals(blankSpace)) {
			throw new Exception("入力してください");
		}
		// 予約チェック
		String roomName = reservationAddData.getRoom_name();
		Timestamp startTime = reservationAddData.getStart_time();
		Timestamp endTime = reservationAddData.getEnd_time();
		int countDuplicateTime = reservationRepository.countDuplicateTime(roomName, startTime, endTime);
		if (countDuplicateTime != 0) {
			throw new Exception("もう利用予約された時間です。");
		}

		reservationAddData.setRoom_id(roomId);
		reservationAddData.setCreate_time(tsCurrentTime);
		Reservationdata reserveData = reservationRepository.save(reservationAddData);
		return reserveData;
	}

	/**
	 * @author イーソンハク
	 * @param Long reserve_id
	 * @return Reservationdata reserveData
	 */
	// 会議室の予約情報の取得
	@Override
	public Reservationdata GetReserveInfo(Long reserve_id) {
		Reservationdata reserveData = reservationRepository.findReserveInfoByReserveId(reserve_id);
		return reserveData;
	}

	/**
	 * @author イーソンハク
	 * @param Long room_id, Timestamp tsToday, Timestamp tsTomorrow
	 * @return List<Reservationdata> reserveDayList
	 * @throws Exception
	 */
	// 会議室の当日予約情報の取得
	@Override
	public List<Reservationdata> GetRoomDayReserve(Long room_id, Timestamp tsToday, Timestamp tsTomorrow) throws Exception {
		List<Reservationdata> reserveDayList = null;
		try {
			reserveDayList = reservationRepository.findReserveInfoByroomId(room_id, tsToday, tsTomorrow);
		}
		catch(Exception e) {
			System.out.println("dd");
			throw new Exception("データがありません。");
		}
		return reserveDayList;
	}
}