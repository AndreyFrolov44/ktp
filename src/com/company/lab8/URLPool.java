package com.company.lab8;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class URLPool {
    private HashMap<String, URLDepthPair> links = new HashMap<>();
    private LinkedList<URLDepthPair> pool = new LinkedList<>();
    private int depth = 0;
    private int workers;

    public URLPool(String url, int depth_, int workers_){
        workers = workers_;
        depth = depth_;
        pool.add(new URLDepthPair(url, 0));
    }

    public void start(){
        while (pool.size() > 0) {
            if(Thread.activeCount() < workers){
                URLDepthPair link = pool.pop();
                Thread task = new Thread(new CrawlerTask(link));
                task.start();
                synchronized (task){
                    try {
                        task.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.printf("Found %d URLS\n", links.size());
    }

    public static Pattern LINK_REGEX = Pattern.compile(
            "<a\\s+(?:[^>]*?\\s+)?href=([\"'])(.*?)\\1"
    );

    private class CrawlerTask implements Runnable{
        URLDepthPair currentLink;

        CrawlerTask(URLDepthPair link){
            this.currentLink = link;
        }

        @Override
        public void run() {

            synchronized (this) {
                    if (links.containsKey(currentLink.getURL())) {
                        URLDepthPair knownLink = links.get(currentLink.getURL());
                        knownLink.incrementVisited();
                        return;
                    }

                    System.out.println(currentLink);

                    links.put(currentLink.getURL(), currentLink);

                    if (currentLink.getDepth() >= depth)
                        return;

                    try {
                        URL url = new URL(currentLink.getURL());
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        con.setRequestMethod("GET");
                        Scanner s = new Scanner(con.getInputStream());
                        while (s.findWithinHorizon(LINK_REGEX, 0) != null) {
                            String newURL = s.match().group(2);
                            if (newURL.startsWith("/"))
                                newURL = currentLink.getURL() + newURL;
                            else if (!newURL.startsWith("http"))
                                continue;
                            URLDepthPair newLink = new URLDepthPair(newURL, currentLink.getDepth() + 1);
                            pool.add(newLink);
                            pool.remove(currentLink);
                        }
                    } catch (Exception e) {}
                    notify();

            }

        }
    }

    public static void showHelp() {
        System.out.println("usage: java Crawler <URL> <depth>");
        System.exit(1);
    }

    public static void main(String[] args) {
        if (args.length != 3) showHelp();

        String url = args[0];
        int depth = 0;
        int workers = 2;

        try {
            depth = Integer.parseInt(args[1]);
            workers = Integer.parseInt(args[2]);

        } catch (Exception e) {
            showHelp();
        }

        URLPool urlPool = new URLPool(url, depth, workers);
        urlPool.start();
    }
}
