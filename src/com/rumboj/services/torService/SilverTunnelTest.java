package com.rumboj.services.torService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.silvertunnel_ng.netlib.api.NetFactory;
import org.silvertunnel_ng.netlib.api.NetLayer;
import org.silvertunnel_ng.netlib.api.NetLayerIDs;
import org.silvertunnel_ng.netlib.api.NetSocket;
import org.silvertunnel_ng.netlib.api.util.TcpipNetAddress;
import org.silvertunnel_ng.netlib.util.ByteArrayUtil;
import org.silvertunnel_ng.netlib.util.HttpUtil;

public class SilverTunnelTest {
	public static void main(String args[]) {
		
		try{
			NetLayer lowerNetLayer = NetFactory.getInstance().getNetLayerById(
				NetLayerIDs.TOR);

		// wait until TOR is ready (optional) - this is only relevant for
		// anonymous communication:
		lowerNetLayer.waitUntilReady();

		// prepare parameters (http default port: 80)
		TcpipNetAddress httpServerNetAddress = new TcpipNetAddress(
				"google.com", 80);
		String pathOnHttpServer = "";
		long timeoutInMs = 5000;

		// do the HTTP request and wait for the response
		byte[] responseBody = HttpUtil.getInstance().get(lowerNetLayer,
				httpServerNetAddress, pathOnHttpServer, timeoutInMs);

		// print out the response
		System.out.println(new String(responseBody));
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
