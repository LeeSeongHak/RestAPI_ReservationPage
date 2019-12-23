package com.kico.reservation.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * @author イーソンハク
 */
@Entity
@Table(name = "reservation")
@Setter
@Getter
@NamedQueries({
		@NamedQuery(name = "Reservationdata.countDuplicateTime", query = "Select count(re) from Reservationdata re where re.room_name = ?1 and re.end_time >= ?2 and re.start_time <= ?3"),
		@NamedQuery(name = "Reservationdata.findReserveInfoByReserveId", query = "Select re from Reservationdata re where re.reserve_id = ?1"),
		@NamedQuery(name = "Reservationdata.findReserveInfoByroomId", query = "Select re from Reservationdata re where re.room_id = ?1 and re.start_time >= ?2 and re.end_time < ?3") })
public class Reservationdata {

	public Reservationdata() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reserve_id;

	@Column(name = "reserve_name" )
	private String reserve_name;

	@NotNull
	@Column(name = "room_id")
	private Long room_id;

	@NotNull
	@Column(name = "room_name")
	private String room_name;

	@NotNull
	@Column(name = "start_time")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm", timezone = "JST")
	private Timestamp start_time;

	@NotNull
	@Column(name = "end_time")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm", timezone = "JST")
	private Timestamp end_time;

	@NotNull
	@Column(name = "create_user")
	private String create_user;

	@NotNull
	@Column(name = "create_time")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm", timezone = "JST")
	private Timestamp create_time;

	@Column(name = "update_user")
	private String update_user;

	@Column(name = "update_time")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm", timezone = "JST")
	private Timestamp update_time;
}