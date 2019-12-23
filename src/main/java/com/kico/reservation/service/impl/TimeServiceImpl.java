package com.kico.reservation.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.kico.reservation.service.TimeService;

@Service
public class TimeServiceImpl implements TimeService {

	/**
	 * @author イーソンハク
	 * @return Timestamp tsCurrentDate
	 * @exception ParseException
	 */
	//現在時間
	@Override
	public Timestamp CurrentTime() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		String dateString = dateFormat.format(date);
		Date today = null;
		try {
			today = dateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Timestamp tsCurrentDate = new Timestamp(today.getTime());
		return tsCurrentDate;
	}

	/**
	 * @author イーソンハク
	 * @return Timestamp tsTodayDate
	 * @exception ParseException
	 */
	//今日の日付
	@Override
	public Timestamp TodayDate() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String dateString = dateFormat.format(date);
		Date today = null;
		try {
			today = dateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Timestamp tsTodayDate = new Timestamp(today.getTime());
		return tsTodayDate;
	}

	/**
	 * @author イーソンハク
	 * @return Timestamp tsTomorrowDate
	 * @exception ParseException
	 */
	//明日の日付
	@Override
	public Timestamp TomorrowDate() {
		Timestamp ts = TodayDate();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(ts);
		cal.add(Calendar.DATE, 1);
		String calString = dateFormat.format(cal.getTime());
		Date tomorrow = null;
		try {
			tomorrow = dateFormat.parse(calString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Timestamp tsTomorrowDate = new Timestamp(tomorrow.getTime());
		return tsTomorrowDate;
	}

	/**
	 * @author イーソンハク
	 * @param Timestamp startTime, Timestamp endTime
	 * @return Timestamp tsTodayDate
	 * @exception データ入力エラー
	 */
	//時間比較
	@Override
	public void timeComparison(Timestamp startTime, Timestamp endTime) throws Exception {
		if (endTime.getTime() - startTime.getTime() <= 0) {
			throw new Exception("開始時間が終了時間より遅れます。確認してください。");
		}
	}
}