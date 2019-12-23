package com.kico.reservation.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kico.reservation.model.RoomRepository;
import com.kico.reservation.model.Roomdata;
import com.kico.reservation.service.RoomIOService;

@Service
public class RoomIOServiceImpl implements RoomIOService {

	@Autowired
	RoomRepository roomRepository;

	private String blankSpace = "";

	/**
	 * @author イーソンハク
	 * @param Roomdata roomAddData, Timestamp tsCurrentTime
	 * @return Roomdata roomData
	 * @exception データ入力エラー
	 */
	//会議室の追加
	@Override
	public Roomdata AddRoom(Roomdata roomAddData, Timestamp tsCurrentTime) throws Exception {
		if(roomAddData.getRoom_name().equals(blankSpace) || roomAddData.getCreate_user().equals(blankSpace)) {
			throw new Exception("入力してください");
		}
		roomAddData.setCreate_time(tsCurrentTime);
		Roomdata roomData = roomRepository.save(roomAddData);
		return roomData;
	}

	/**
	 * @author イーソンハク
	  * @return List<Roomdata> roomList
	 */
	//会議室のリスト取得
	@Override
	public List<Roomdata> GetRoomList() {
		List<Roomdata> roomList = roomRepository.findAll();
		return roomList;
	}

	/**
	 * @author イーソンハク
	 * @param String roomName
	 * @return Long roomId
	 */
	//会議室名によるID取得
	@Override
	public Long findRoomIdByRoomName(String roomName) {
		Roomdata roomData = roomRepository.findRoomIdByRoomName(roomName);
		Long roomId = roomData.getRoom_id();
		return roomId;
	}
}