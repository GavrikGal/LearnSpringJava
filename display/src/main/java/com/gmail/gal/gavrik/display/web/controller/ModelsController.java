package com.gmail.gal.gavrik.display.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.gmail.gal.gavrik.display.domain.Models;
import com.gmail.gal.gavrik.display.service.ModelsService;


@RequestMapping("/models")
@Controller
public class ModelsController {
	
	final Logger			logger	= LoggerFactory.getLogger(ModelsController.class);
	
	@Autowired
	private ModelsService modelsService;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value = "/photo/{id}", method = RequestMethod.GET)
	@ResponseBody
	public byte[] downloadPhoto(@PathVariable("id") Long id) {
		Models models = modelsService.findById(id);

		if (models.getPhoto() != null) {
			logger.info("Downloading photo for id: {} with size {}", models.getIdModel(),
					models.getPhoto().length);
		}
		return models.getPhoto();
	}


}
