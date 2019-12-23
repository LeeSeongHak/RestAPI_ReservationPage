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
@Table(name = "reservation_room")
@Setter
@Getter
@NamedQueries({
		@NamedQuery(name = "Roomdata.findRoomIdByRoomName", query = "Select r from Roomdata r where r.room_name = ?1") })
public class Roomdata {

	public Roomdata() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long room_id;

	@NotNull
	@Column(name = "room_name", nullable = false)
	private String room_name;

	@NotNull
	@Column(name = "create_user", nullable = false)
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
