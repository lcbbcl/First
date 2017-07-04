package com.my.pack;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.DateUtils;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.message.BasicHeader;

public class HttpCinet {
		
	/**
	 * 异步的HttpClient
	 */
	public void asyncClient() throws ClientProtocolException, IOException{

		HttpPost httpPost = new HttpPost("http://localhost:8080/post");
		
		/*
		 * timeout
		 */
		RequestConfig requestConfig = RequestConfig.custom()  
		        .setConnectTimeout(5000).setConnectionRequestTimeout(5000)  
		        .setSocketTimeout(600000).build();
		httpPost.setConfig(requestConfig);
		
		
				    		    
		//声明异步的传输客户端    
		CloseableHttpAsyncClient asyncHttpClient = HttpAsyncClientBuilder.create()
				.setDefaultRequestConfig(requestConfig)
				.build();		    
		  
		
		
		//转为字节流
		String str1="sssdfsdf";
		ByteArrayInputStream str1Btye  = new ByteArrayInputStream(str1.getBytes(Charset.forName("utf-8")));
		BufferedInputStream str1Buffer = new BufferedInputStream(str1Btye);

		
		MultipartEntityBuilder builder = MultipartEntityBuilder.create(); 
		//这里可以传输字段信息
		builder.addTextBody("Name", "myName");
		builder.addTextBody("modified", "20160702");
		//这里可以传输字节流
		builder.addBinaryBody("bufferedInputStream", str1Buffer);
		HttpEntity httpEntity = builder.build();
		/*
		 * HttpEntity转成BufferedHttpEntity
		 * 因为当HttpEntity当中有字节流时，在服务端如果超时了重试时，会抛NonRepeatableRequestException，此时实体不能重试发送请求
		 * HttpEntity是非repeteable的,应换成BufferedHttpEntity去传送
		 */
		BufferedHttpEntity bufferedHttpEntity = new BufferedHttpEntity (httpEntity);
		
		httpPost.setEntity(bufferedHttpEntity);
		
		Header headers = new BasicHeader(HttpHeaders.DATE, DateUtils.formatDate(new Date(),DateUtils.PATTERN_RFC1123));
		httpPost.setHeader(headers);
		
		  asyncHttpClient.start();
		  asyncHttpClient.execute(httpPost, new FutureCallback<HttpResponse>(){

			@Override
			public void completed(HttpResponse result) {
				//to do something when completed
				
				
			}

			@Override
			public void failed(Exception ex) {
				//to do something when failed
				
			}

			@Override
			public void cancelled() {
				//to do sonething when cancelled
			}
			
		});
		
		IOUtils.closeQuietly(str1Btye);
		IOUtils.closeQuietly(str1Buffer);
		IOUtils.closeQuietly(asyncHttpClient);

	}
	
	
	/*
	 * 同步的客户端
	 */
	public void client() throws IOException{
		
		HttpPost httpPost = new HttpPost("http://localhost:8080/example");
		
		/*
		 * setting timeout
		 */
		RequestConfig requestConfig = RequestConfig.custom()  
		        .setConnectTimeout(5000).setConnectionRequestTimeout(1000)  
		        .setSocketTimeout(2000).build();
		httpPost.setConfig(requestConfig);
		
		
		
		CloseableHttpClient closeableHttpClient = HttpClientBuilder.create()
	    .setDefaultRequestConfig(requestConfig)
	    .setRetryHandler(new DefaultHttpRequestRetryHandler())
	    .build();
		

		
		MultipartEntityBuilder builder = MultipartEntityBuilder.create(); 
		builder.addTextBody("param", "xxxx");
		HttpEntity httpEntity = builder.build();
		/*
		 * HttpEntity转成BufferedHttpEntity
		 * 因为当HttpEntity当中有字节流时，在服务端如果超时了重试时，会抛NonRepeatableRequestException，此时实体不能重试发送请求
		 * HttpEntity是非repeteable的,应换成BufferedHttpEntity去传送
		 */
		BufferedHttpEntity bufferedHttpEntity = new BufferedHttpEntity (httpEntity);
		httpPost.setEntity(bufferedHttpEntity);
		//HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
		closeableHttpClient.close();
		
	}
	
	
			
	
}
