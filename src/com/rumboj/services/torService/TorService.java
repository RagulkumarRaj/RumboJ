package com.rumboj.services.torService;

import com.subgraph.orchid.TorClient;
import com.subgraph.orchid.TorInitializationListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TorService {
	private static TorClient client;

public static void main(String[] args) {
    startOrchid();
}

private static void startOrchid() {
    //listen on 127.0.0.1:9150 (default)
    client = new TorClient();
    client.addInitializationListener(createInitalizationListner());
    client.start();
    client.enableSocksListener();//or client.enableSocksListener(yourPortNum);
}

private static void stopOrchid() {
    client.stop();
}

public static TorInitializationListener createInitalizationListner() {
    return new TorInitializationListener() {
        @Override
        public void initializationProgress(String message, int percent) {
            System.out.println(">>> [ " + percent + "% ]: " + message);
        }

        @Override
        public void initializationCompleted() {
            System.out.println("Tor is ready to go!");
            doTests();
        }
    };
}

private static void doTests() {
    testOrchidUsingProxyObject();
    //testOrchidUsingSystemPropsProxy();
    //testOrchidUsingSocket();
}

private static void testOrchidUsingProxyObject() {
    Thread thread = new Thread() {
        @Override
        public void run() {
            try {
                //Caution: Native Java DNS lookup will occur outside of the tor network.  
                //Monitor traffic on port 53 using tcpdump or equivalent.
                URL url = new URL("http://www.whatismyip.net/");
                Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("localhost", 9150));
                HttpURLConnection uc = (HttpURLConnection) url.openConnection(proxy);
                uc.setConnectTimeout(10000);
                Document document = Jsoup.parse(IOUtils.toString(uc.getInputStream()));
                String result = document.text();
                System.out.println("testOrchidUsingProxyObject: " + result);
            } catch (Exception ex) {
                Logger.getLogger(TorService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
    thread.start();
}

private static void testOrchidUsingSystemPropsProxy() {
    Thread thread = new Thread() {
        @Override
        public void run() {
            try {
                //Caution: Native Java DNS lookup will occur outside of the tor network. 
                //Monitor traffic on port 53 using tcpdump or equivalent.
                System.setProperty("socksProxyHost", "127.0.0.1");
                System.setProperty("socksProxyPort", "9150");
                Document document = Jsoup.connect("https://wtfismyip.com/").get();
                String result = document.select("div[id=tor").text();
                System.out.println("testOrchidUsingSystemPropsProxy: " + result);
                System.setProperty("socksProxyHost", "");
                System.setProperty("socksProxyPort", "");
            } catch (Exception ex) {
                Logger.getLogger(TorService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
    thread.start();
}

private static void testOrchidUsingSocket() {
    Thread thread = new Thread() {
        @Override
        public void run() {
            try {
                // This does not appear to leak the DNS lookup, but requires confirmation!
                Socket socket = client.getSocketFactory().createSocket("www.google.com", 80);
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer.println("GET /");
                String line;
                System.out.println("testOrchidUsingSocket: ");
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                socket.close();
            } catch (Exception ex) {
                Logger.getLogger(TorService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
    thread.start();
  }
}
