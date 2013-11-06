package es.upm.dit.gsi.sematch.service;



import java.util.List;
import java.util.Map;

import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;

import es.upm.dit.gsi.sematch.search.QueryConfig;
import es.upm.dit.gsi.sematch.search.Searcher;
import es.upm.dit.gsi.sematch.search.SemanticQuery;
import es.upm.dit.gsi.sematch.similarity.SimilarityService;

public class Searching {
	
	private QueryConfig queryConfig = null;
	private SimilarityService simService = null;
	private Searcher searcher = null;
	private SemanticQuery query = null;

	public Searching(Directory directory){
		searcher = new Searcher(directory);
		//sub query must not be null
		Query q = new MatchAllDocsQuery();
		query = new SemanticQuery(q);
	}
	
	public List<Map<String,String>> search(Map<String,Object> queryData){
		
		queryConfig.setQuery(queryData);
		query.setQueryConfig(queryConfig);
		query.setSimService(simService);
		return searcher.search(query);
	}

	public void setQueryConfig(QueryConfig queryConfig) {
		this.queryConfig = queryConfig;
		this.searcher.setResultSize(queryConfig.getResultSize());
	}

	public void setSimService(SimilarityService simService) {
		this.simService = simService;
	}
	
	
	public void close(){
		searcher.close();
	}

}