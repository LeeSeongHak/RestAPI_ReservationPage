package com.kico.reservation.service;

import java.sql.Timestamp;
import java.util.List;

import com.kico.reservation.model.Roomdata;

/*
 * 開発者:イーソンハク
 * 使用目的：RoomIOServiceのインターフェースとして使用するため
 * 使用方：implementsで宣言後使用
 */
public interface RoomIOService {
	Roomdata AddRoom(Roomdata roomAddData, Timestamp tsCurrentTime) throws Exception;

	List<Roomdata> GetRoomList();

	Long findRoomIdByRoomName(String roomName);
}