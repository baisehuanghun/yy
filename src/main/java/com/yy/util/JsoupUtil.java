package com.yy.util;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 白色黄昏
 * @create 2022-03-19
 */
@Slf4j
public class JsoupUtil {
    public static Document sendGet(String url) {
        return sendGet(url,"");
    }

    public static Document sendGet(String url,String cookie) {
        return sendGetProxy(url,cookie,"",null);
    }

    public static Document sendGetProxy(String url) {
        String cookie = "theme=auto; over18=1; _ym_uid=1648838510586400631; _ym_d=1648838510; locale=zh; _ym_isad=1; __cf_bm=_7JuVMpy7fGw5sFD2j.D74TwydfLE6ft.W.mfrFFmDM-1649753460-0-AaVizz7BjE2odRBcG3OKVUe+CwrS+QIZuIZa5o/yaoEBLV+FYRGm1R9UCxlmn6sTmfSL2QFkK8pecs9F5YmNxdjurrfK08eHiD4xXAWQW6CRks1ovzN+Oix9Uh4I0zCZXg==; _jdb_session=2Ld%2BlCED%2FVeX3aT26F6rH%2FXXZtDSAOdA%2B3K%2FGSSMO324KDJA7pMPIjay8t1oK%2BaaMphFX6bfWqlAmE5pA33EKmjT6IXQZBTJEZTbhR3qVtZsL8cb9RYmgwxuQIh4fseckamZ2P6cHIr%2BdSeBwf6hCRYrH8rI0y0wAlM%2FTRtAyoLkRy2mm6XAhVUmte1MrkWIzZ7F4l9l8SHRfgbuwfPhD3yP5jSUNGnwtrAEaaKZjH4IA3d45kYrEkXn6p1xcxSV335YQ6pvjQbXIdKSMAqJ4lmhxawF0BwXZ4z0D1pANO3%2FmKWjyvKexaDO--MISON1o1%2BdHnYVcE--wF%2F%2Bs%2FJE58OqeljYkl7NHw%3D%3D";

        return sendGetProxy(url,"PHPSESSID=7l62n10csulu0mnq15hqe6u3cl","127.0.0.1",7890);

    }

    public static Document sendGetProxy(String url,String cookie,String host,Integer port) {
        log.info(url);
        final int MAX = 5;
        int time = 0;
        Document doc = null;
        while (time < MAX) {
            Map<String, String> header = new HashMap<String, String>();
            header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            header.put("Accept-Language", "zh-CN,zh;q=0.8");
            header.put("Accept-Encoding", "gzip, deflate, sdch");
            header.put("Accept-Charset", "  GB2312,utf-8;q=0.7,*;q=0.7");
            header.put("Connection", "keep-alive");
            header.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
            if(!"".equals(cookie)){
                header.put("cookie", cookie);
            }
            try {
                Connection connection = Jsoup.connect(url)
                        .ignoreContentType(true)
                        .ignoreHttpErrors(true)
                        .headers(header)
                        .timeout(30000);
                if(!"".equals(host) && port != null){
                    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
                    connection.proxy(proxy);
                }
                doc  =  connection.get();
                return doc;
            } catch (IOException e) {
                log.error(url + " error:" +e.getMessage());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                e.printStackTrace();
            }finally {
                time++;
            }
        }
        return doc;
    }
}
