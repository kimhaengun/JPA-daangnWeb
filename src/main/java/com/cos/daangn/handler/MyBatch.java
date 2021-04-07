package com.cos.daangn.handler;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MyBatch {
	private final ExceptionList exceptionList;
	
	@Scheduled(fixedDelay = 1000*60*10) //10분마다 실행
	public void excute() {
		List<String> exList = exceptionList.getExList();
		//DB에 insert
	exList.clear();
	}
}
