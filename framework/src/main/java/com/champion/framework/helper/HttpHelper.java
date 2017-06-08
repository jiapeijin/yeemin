/**
 * @Description: 
 * @tanggulaTest.httpclient
 * @FileName:HttpHelper.java
 * @Author: William
 * @CreateTime: 2017-04-19 05:42:26
 */
package com.champion.framework.helper;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: 
 * @ClassName: HttpHelper
 * @Auther: William
 * @CreateTime: 2017-04-19 05:42:26
 */
public class HttpHelper {

    private final static Logger logger = LoggerFactory.getLogger(HttpHelper.class);
	
	/**
	 * 
	 * @Description: HTTP POST
	 * @Param: @param url
	 * @Param: @param param
	 * @Param: @return
	 * @ReturnType String
	 * @Author: William
	 * @CreateTime: 2017-04-19 06:26:32
	 */
	public synchronized static String post(String url, Map<String, String> paramMap) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//配置超时时间
        RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(0).setConnectionRequestTimeout(0)
                .setSocketTimeout(0).setRedirectsEnabled(true).build();
         
        HttpPost httpPost = new HttpPost(url);
        //设置超时时间
        httpPost.setConfig(requestConfig);
        //装配post请求参数
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
        	list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        String strResult = "";
        try {
            if (logger.isDebugEnabled()) {
                logger.info("post data to url{ url: " + url + ", data: " + paramMap);
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8"); 
            //设置post求情参数
            httpPost.setEntity(entity);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if(httpResponse != null){ 
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    strResult = EntityUtils.toString(httpResponse.getEntity());
                } else if (httpResponse.getStatusLine().getStatusCode() == 400) {
                    strResult = EntityUtils.toString(httpResponse.getEntity());
                } else if (httpResponse.getStatusLine().getStatusCode() == 500) {
                    strResult = EntityUtils.toString(httpResponse.getEntity());
                } else {
                    strResult = EntityUtils.toString(httpResponse.getEntity());
                } 
            }else{
                 return null;
            }
        } catch (Exception e) {
            logger.warn("post data warn: " + e.getMessage());
        }finally {
            try {
                if(httpClient != null){
                    httpClient.close(); //释放资源
                }
            } catch (IOException e) {
                logger.warn("close http client warn: " + e.getMessage());
            }
        }
        return strResult;
	}
}
