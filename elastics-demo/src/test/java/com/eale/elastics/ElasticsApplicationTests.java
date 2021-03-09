package com.eale.elastics;

import com.alibaba.fastjson.JSON;
import com.eale.elastics.model.User;
import net.minidev.json.JSONObject;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexAction;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ElasticsApplicationTests {


	@Autowired
	RestHighLevelClient client;

	@Test
	void contextLoads() {
	}


	/**
	 * 新建索引
	 * @throws Exception
	 */
	@Test
	void testCreateIndex() throws IOException {
		//创建请求
		CreateIndexRequest request = new CreateIndexRequest("testapi");
		CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
	}

	/**
	 * 查询索引
	 * @throws IOException
	 */
	@Test
	void testFindIndex() throws IOException {
		GetIndexRequest request = new GetIndexRequest("testapi");
		boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
		System.out.println(exists);

	}

	/**
	 * 删除索引
	 * @throws IOException
	 */
	@Test
	void testDeleteIndex() throws IOException {
		DeleteIndexRequest request = new DeleteIndexRequest("testapi");
		AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
		System.out.println(delete);
	}

	/**
	 * 创建文档
	 * @throws IOException
	 */
	@Test
	void testCreateDocument() throws IOException {
		IndexRequest indexRequest = new IndexRequest("testapi");
		User user = new User("张飞","射手");
		IndexRequest source = indexRequest.source(JSON.toJSONString(user), XContentType.JSON);
		IndexResponse index = client.index(indexRequest, RequestOptions.DEFAULT);
		System.out.println(index.toString());
	}

	/**
	 * 文档是否存在
	 * @throws IOException
	 */
	@Test
	void testExistDocument() throws IOException {
		//testapi 索引中     是否存在 1 的文档
		GetRequest getRequest = new GetRequest("testapi","DDjQF3gBKibW882hvjfT");
		boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
		System.out.println(exists);
	}


	/**
	 * 获取文档信息
	 * @throws IOException
	 */
	@Test
	void testGetDocument() throws IOException {
		GetRequest getRequest = new GetRequest("testapi", "CjjNF3gBKibW882h9jcV");
		GetResponse documentFields = client.get(getRequest, RequestOptions.DEFAULT);
		System.out.println(documentFields.getSource());
	}

	/**
	 * 获取文档信息
	 * @throws IOException
	 */
	@Test
	void testUpdatDocument() throws IOException {
		UpdateRequest updateRequest = new UpdateRequest("testapi", "CjjNF3gBKibW882h9jcV");
		User user = new User("张飞","坦克");
		updateRequest.doc(JSON.toJSONString(user),XContentType.JSON);
		UpdateResponse update = client.update(updateRequest, RequestOptions.DEFAULT);
		System.out.println(update.status());
	}

	/**
	 * 删除文档信息
	 * @throws IOException
	 */
	@Test
	void testDeleteDocument() throws IOException {
		DeleteRequest deleteRequest = new DeleteRequest("testapi", "CjjNF3gBKibW882h9jcV");
		DeleteResponse delete = client.delete(deleteRequest, RequestOptions.DEFAULT);
		System.out.println(delete.status());
	}

	/**
	 * 查询文档
	 */
	@Test
	void testSearchDocument() throws IOException {
		SearchRequest searchRequest = new SearchRequest("testapi");
		//匹配字段
		MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "张飞");
		//构建查询器
		searchRequest.source(new SearchSourceBuilder().query(matchQueryBuilder));
		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		SearchHits hits = searchResponse.getHits();
		System.out.println(JSON.toJSONString(hits.getHits()));
		SearchHit[] hits1 = hits.getHits();
		for (int i = 0; i < hits1.length; i++) {
			System.out.println(JSON.toJSONString(hits1[i]));
		}
		System.out.println(hits.getTotalHits());
	}

}
