package com.cowcow.demo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;


public class WordUtil {

	
	public static Map<String, String> splitWord(File file) {
		Map<String, String> map = new HashMap<>();
		FileReader reader = null;
		try {
			reader = new FileReader(file);
			int word = 0;
			String key = "";
			while ((word = reader.read()) != -1) {
				key = (char) word + "";
				if (map.containsKey(key)) {
					map.put(key, (Long.parseLong(map.get(key)) + 1) + "");
				} else {
					map.put(key, "1");
				}
			}
			//需要关闭流，否则后面的文件将无法删除（delete返回false）
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;

	}

	public static Map<String, Long> splitWord() {

		Map<String, Long> map = new HashMap<>();
		try {
			File file = ResourceUtils.getFile("classpath:static/doc/01史记.txt");
			FileReader reader = new FileReader(file);
			int word = 0;
			String key = "";
			while ((word = reader.read()) != -1) {
				key = (char) word + "";
				if (map.containsKey(key)) {
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

	public static Map<String, Long> splitWord(String filename) {

		Map<String, Long> map = new HashMap<>();
		try {
			File file = ResourceUtils.getFile("classpath:static/doc/" + filename);
			FileReader reader = new FileReader(file);
			int word = 0;
			String key = "";
			while ((word = reader.read()) != -1) {
				key = (char) word + "";
				if (map.containsKey(key)) {
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
	
	public static ArrayList<Map<String, String>> splitFiles() {

		File file = null;
		ArrayList<Map<String, String>> allWord = new ArrayList<Map<String, String>>();
		try {
			file = ResourceUtils.getFile("E:/resource/doc/history/");
			//System.out.println(file.getAbsolutePath());
			if (file.exists() && file.isDirectory()) {
				File[] files = file.listFiles();
				for (File file2 : files) {
					Map<String, String> wordMap = null;
					if (file2.isFile()) {
						// System.out.println(file2.getName());
						//读取文件需要使用文件而不是文件名，否则会出现文件拒绝访问的报错
						wordMap = splitWord(file2);
					}
					if(wordMap.size() > 0){
						wordMap.put("filename", file2.getName());
						allWord.add(wordMap);
					}
					File origin = ResourceUtils.getFile("E:/resource/doc/history/" + file2.getName());
					File bak = ResourceUtils.getFile("E:/resource/doc/history_bak/" + file2.getName());
					if(!bak.exists()){
						bak.createNewFile();
					}
					//读取文件后备份
					//origin.renameTo(bak);
					FileCopyUtils.copy(origin, bak);
					if(origin.exists() && origin.isFile()){
						System.out.println(origin.delete());
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allWord;
	}
	
	public static List<String> getDataByPoint(String article){
		
		List<String> list = new ArrayList<>();
		String [] sentences = article.replaceAll("\r\n", "").split("。");
		for(String sentence : sentences){
			list.add(sentence);
		}
		return list;
	}
	
	public static String getSingleArticle(File file){
		
		StringBuffer content = new StringBuffer();
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
			char [] once = new char[4];
			int count = 0;
			while((count = fileReader.read(once)) != -1){
				content.append(new String(once,0,count));
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content.toString();
	}
	
	public static Map<String, String> getArticle(){
		
		Map<String, String> contents = new HashMap<>();
		File file = null;
		try {
			file = ResourceUtils.getFile("E:/resource/doc/history/");
			if (file.exists() && file.isDirectory()) {
				File[] files = file.listFiles();
				for(File file2 : files) {
					if (file2.isFile()) {
						// System.out.println(file2.getName());
						//读取文件需要使用文件而不是文件名，否则会出现文件拒绝访问的报错
						//wordMap = splitWord(file2);
						contents.put(file2.getName(), getSingleArticle(file2));
					}
					File origin = ResourceUtils.getFile("E:/resource/doc/history/" + file2.getName());
					File bak = ResourceUtils.getFile("E:/resource/doc/history_bak/" + file2.getName());
					if(!bak.exists()){
						bak.createNewFile();
					}
					//读取文件后备份
					//origin.renameTo(bak);
					FileCopyUtils.copy(origin, bak);
					if(origin.exists() && origin.isFile()){
						System.out.println(origin.delete());
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contents;
	}	
}
