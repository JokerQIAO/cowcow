package com.cowcow.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cowcow.demo.dao.ArticleDao;
import com.cowcow.demo.dao.SentenceDao;
import com.cowcow.demo.dao.WordDao;
import com.cowcow.demo.entity.ArticleEntity;
import com.cowcow.demo.entity.SentenceEntity;
import com.cowcow.demo.entity.WordEntity;
import com.cowcow.demo.util.WordUtil;

@Service
public class WordScheduleTaskService {

	@Autowired
	WordDao wordDao;
	
	@Autowired
	ArticleDao articleDao;
	
	@Autowired
	SentenceDao sentenceDao;
	
	//启动即执行
	//@Scheduled(fixedRate = 5000)
	public void articleTask(){

		Map<String, String> articleMap = WordUtil.getArticle();
		for(String key : articleMap.keySet()){
			ArticleEntity articleEntity = new ArticleEntity();
			articleEntity.setContent(articleMap.get(key));
			articleEntity.setFilename(key);
			articleDao.save(articleEntity);
		}
	}
	
	//@Scheduled(cron = "0 40 8 * * *") 
	//select count(s.sentence) , s.sentence from sentence s group by sentence having count(s.sentence) > 20;
	public void splitByPoint(){
		List<ArticleEntity> articleEntities = articleDao.findAll();
		for (ArticleEntity articleEntity : articleEntities) {
			List<String> sentences = WordUtil.getDataByPoint( articleEntity.getContent());
			for(String key : sentences){
				if(key.length() < 255/3) {
					SentenceEntity sentenceEntity = new SentenceEntity();
					sentenceEntity.setFilename(articleEntity.getFilename());
					sentenceEntity.setSentence(key);
					sentenceDao.save(sentenceEntity);
				}
			}
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
