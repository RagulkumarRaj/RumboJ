package com.rumboj.restcontroller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.rumboj.crawlers.main.BotDelegatorController;
import com.rumboj.services.comparisonService.AbstractComparator;
import com.rumboj.services.comparisonService.PhoneComparator;
import com.rumboj.services.dataService.InMemoryTextSearchEngine;

@Controller
@RequestMapping("/")
public class FrontController {

	private InMemoryTextSearchEngine textSearchEngine = InMemoryTextSearchEngine.getInstance();
	private AbstractComparator phoneComparator = new PhoneComparator();
	
	@RequestMapping(value = "products/{name}", method = RequestMethod.GET)
	public String getProductDetails(@PathVariable String name, ModelMap model) {
		List<JsonObject> products = phoneComparator.getAggregateResult(name);
		JsonObject product;
		if(products.size() > 0){
			 product = products.get(0);	
		}
		else{
			product = new JsonObject();
		}
		model.addAttribute("product", product);
		//Get the product information from the directory, prod details, whichever websites the product is found
		return "product";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam("username") String username, @RequestParam("password") String password, ModelMap model) {
		model.addAttribute("username", username);
		model.addAttribute("password", password);
		return "welcome";
	}
	
	@RequestMapping(value = "/hc", method = RequestMethod.GET)
	public String healthCheck(ModelMap model) {
		//model.addAttribute("movie", name);
		//Get the product information from the directory, prod details, whichever websites the product is found
		return "";
	}
	
	@RequestMapping(value = "/version", method = RequestMethod.GET)
	public String versionCheck(ModelMap model) {
		return "";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getWelcomePage(ModelMap model) {
		model.addAttribute("msg", "Meta Search Shopping");
		return "welcome";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchProduct(@RequestParam String searchstring, ModelMap model) {
		//Search something for loading image.
		//Maintain a separate service that will return images based on title
		//No complete logic under controller class..Remember
		//Call something productComparator getAggregateResults 
		//No business logic here....Remember
		//List<JsonObject> prods = textSearchEngine.getSearchResults(searchstring);
		List<JsonObject> products = phoneComparator.getAggregateResult(searchstring);
		JsonObject product;
		if(products.size() == 0){
			product = new JsonObject();
		}
		else{
			product = products.get(0);
		}
		model.addAttribute("products", products);
		return "welcome";
		/*
		final JsonObject product = new JsonObject();
		product.addProperty("website", "flipkart");
		product.addProperty("title", "Nexus");
		product.addProperty("price", "25000");
		product.addProperty("processor", "2GHZ");
		product.addProperty("align", "right");
		product.addProperty("screensize", "5.5 Inch");
		product.addProperty("RAM", "2 GB");
		product.addProperty("battery", "2160mah");
		product.addProperty("flipkartPrice", "22000");
		product.addProperty("amazonPrice", "26000");
		model.addAttribute("product", product);
		return "product";
		*/
	}

	@RequestMapping(value = "/crawl/{task}", method = RequestMethod.GET)
	@ResponseBody
	public String startCrawl(@PathVariable String task) {
		//model.ad/dAttribute("productname", productname);
		if(task.equals("start")){
			BotDelegatorController.startAllCrawlers();
			return "started";
		}
		else if(task.equals("stop")){
			BotDelegatorController.stopAllTasks();
			return "stopped";
		}
		return "";
		//BotDelegatorController.start();
		//return "";
	}
	
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test(ModelMap model) {
		final JsonObject product = new JsonObject();
		product.addProperty("website", "flipkart");
		product.addProperty("title", "Nexus");
		product.addProperty("price", "25000");
		product.addProperty("proc", "2GHZ");
		product.addProperty("align", "right");
		model.addAttribute("product", product);
		return "test";
	}
}
