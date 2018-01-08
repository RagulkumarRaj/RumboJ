package com.rumboj.services.comparisonService;

import java.util.List;

import com.google.gson.JsonObject;
import com.rumboj.services.dataService.InMemoryTextSearchEngine;

public abstract class AbstractComparator {
	InMemoryTextSearchEngine searchEngine = InMemoryTextSearchEngine.getInstance();
     public abstract List<JsonObject> getAggregateResult(String searchString);
     public abstract JsonObject prepareComparisonData(String website, String productTitle, String data, String price);
}
