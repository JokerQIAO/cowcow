package com.cowcow.demo.controller;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cowcow.demo.dao.WordDao;
import com.cowcow.demo.entity.WordEntity;
import com.cowcow.demo.util.WordUtil;


@Controller
@RequestMapping("/word")
public class WordController {

	@Autowired
	WordDao wordDao;
	
	@RequestMapping("/show")
	public String showWord(){
		
		Map<String, Long> wordMap = WordUtil.splitWord();
		
		for (String key : wordMap.keySet()) {
			//System.out.println(key + " : " + wordMap.get(key));
			WordEntity wordEntity = new WordEntity();
			wordEntity.setCount(wordMap.get(key));
			wordEntity.setFilename("01史记.txt");
			wordEntity.setText(key);
			wordDao.save(wordEntity);
		}
		
		return "showword";
	}
}
