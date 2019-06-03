package com.cowcow.demo.service;

import java.util.ArrayList;
import java.util.Map;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cowcow.demo.dao.ArticleDao;
import com.cowcow.demo.dao.WordDao;
import com.cowcow.demo.entity.ArticleEntity;
import com.cowcow.demo.entity.WordEntity;
import com.cowcow.demo.util.WordUtil;

@Service
public class WordScheduleTaskService {

	@Autowired
	WordDao wordDao;
	
	@Autowired
	ArticleDao articleDao;
	
	//启动即执行
	@Scheduled(fixedRate = 5000)
	public void articleTask(){

		Map<String, String> articleMap = WordUtil.getArticle();
		for(String key : articleMap.keySet()){
			ArticleEntity articleEntity = new ArticleEntity();
			articleEntity.setContent(articleMap.get(key));
			articleEntity.setFilename(key);
			articleDao.save(articleEntity);
		}
	}
	
	//@Scheduled(fixedRate = 500000)
	public void wordSplitTask(){
		//System.out.println("---------task begin!");
		//遍历文件夹，得到文件列表
		//遍历文件，依次解析
		ArrayList<Map<String, String>> allWord = WordUtil.splitFiles();
		for (Map<String, String> wordMap : allWord) {
			String filename = wordMap.get("filename");
			wordMap.remove("filename");
			for (String key : wordMap.keySet()) {
				WordEntity wordEntity = new WordEntity();
				wordEntity.setCount(Long.parseLong(wordMap.get(key)));
				wordEntity.setFilename(filename);
				wordEntity.setText(key);
				wordDao.save(wordEntity);
			}
		}
		
	}
}
