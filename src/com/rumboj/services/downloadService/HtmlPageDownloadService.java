package com.rumboj.services.downloadService;

public interface HtmlPageDownloadService {
    public String downloadPageAsString(String url);
    public String downloadPageWithProxyAsString(String url, String proxy, String port, String proxyType);
}
