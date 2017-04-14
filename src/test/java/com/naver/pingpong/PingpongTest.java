package com.naver.pingpong;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by AD on 2017-03-31.
 */
public class PingpongTest {
	Pingpong pingpong = new Pingpong();

	@Test
	public void 핑퐁테스트() {
		System.out.println(pingpong.pingpong(8));
		System.out.println(pingpong.pingpong(22));
		System.out.println(pingpong.pingpong(68));
		System.out.println(pingpong.pingpong(100));
	}

	@Test
	public void 핑퐁2테스트() {
		System.out.println(pingpong.pingpong2(8));
		System.out.println(pingpong.pingpong2(22));
		System.out.println(pingpong.pingpong2(68));
		System.out.println(pingpong.pingpong2(100));
	}

}