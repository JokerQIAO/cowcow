package com.cowcow.demo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.ResourceUtils;

public class WordUtil {

	public static Map<String, Long> splitWord(){
		
		Map<String, Long> map = new HashMap<>();
		try {
			File file = ResourceUtils.getFile("classpath:static/doc/01史记.txt");
			FileReader reader = new FileReader(file);
			int word = 0;
			String key = "";
			while((word = reader.read()) != -1){
				key = (char)word + "";
				if(map.containsKey(key)){
					map.put(key, map.get(key) + 1);
				} else {
					map.put(key, 1L);
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
				
		return map;
	}
}
