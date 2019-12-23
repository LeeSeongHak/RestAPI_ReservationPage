package com.kico.reservation.service;

import java.sql.Timestamp;

/*
 * 開発者:イーソンハク
 * 使用目的：TimeServiceのインターフェースとして使用するため
 * 使用方：implementsで宣言後使用
 */
public interface TimeService {
	Timestamp CurrentTime();

	Timestamp TodayDate();

	Timestamp TomorrowDate();

	void timeComparison(Timestamp startTime, Timestamp endTime) throws Exception;
}