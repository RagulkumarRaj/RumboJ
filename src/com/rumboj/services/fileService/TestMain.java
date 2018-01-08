package com.rumboj.services.fileService;

import java.util.List;

public class TestMain {

	public static void main(String[] args) {
		List<String> list = StaticDataLoader.readAllPropertyFileLines("rumboj.amazon.starturls.list");
        list.forEach(System.out::println);
	}
}
