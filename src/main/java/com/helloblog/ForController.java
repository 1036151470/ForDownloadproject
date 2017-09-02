package com.helloblog;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
@Scope("prototype")
public class ForController {

    private long progress = 0;
    private String name;
    private String path;
    private String size;
    private int repatenum = 0;
    private int continuenum = 0;


    public String justgorun(String urlc, String pathc,String forid) throws Exception {

        URL url = new URL(urlc);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        String filename = urlc.replaceAll(".*/", "");
        File file = new File(pathc + File.separator + filename);

        if (file.length() == connection.getContentLengthLong()) {
            return "ok";
        }

        if (file.length() > connection.getContentLengthLong()) {
            return "ok";
        }


        if (file.exists()) {
            continuedownload(urlc, pathc,forid);
        } else {
            justfordownload(urlc, pathc,forid);
        }
        return "ok";
    }

    public void repate(String urlc, String pathc,String forid) throws Exception {
        System.out.println("error");
        repatenum++;
        Thread.sleep(3000);
        justfordownload(urlc, pathc,forid);
    }

    public void continuerepate(String urlc, String pathc,String forid) throws Exception {
        System.out.println("error");
        continuenum++;
        Thread.sleep(3000);
        continuedownload(urlc, pathc,forid);

    }


    public void justfordownload(String urlc, String pathc,String forid) {
        try {
            File filepath = new File(pathc);
            if (!filepath.exists()) {
                filepath.mkdirs();
            }
            String filename = urlc.replaceAll(".*/", "");
            File file = new File(pathc + File.separator + filename);

            URL url = new URL(urlc);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64;sx64; rv:54.0) Gecko/20100101 Firefox/54.0");
            connection.setRequestMethod("GET");
            int filesize = connection.getContentLength();

            size = String.valueOf(filesize);
            name = filename;
            path = pathc;

            InputStream inputStream = connection.getInputStream();
            FileOutputStream outputStream = new FileOutputStream(pathc + File.separator + filename);
            int len = 0;
            byte[] buff = new byte[1024];
            int countnum = 0;
            while ((len = inputStream.read(buff)) != -1) {
                countnum++;
                if (countnum > 40) {
                    countnum = 0;
                    Progsslist.map.put(forid,(float) file.length() / filesize * 100 );
                }
                outputStream.write(buff, 0, len);
            }
            inputStream.close();
            outputStream.close();
            connection.disconnect();
        } catch (IOException e) {
            try {
                if (repatenum < 4) {
                    repate(urlc, pathc,forid);
                }
            } catch (Exception e1) {

            }
        }
    }


    public void continuedownload(String urlc, String pathc,String forid) {
        try {
            String filename = urlc.replaceAll(".*/", "");
            File file = new File(pathc + File.separator + filename);
            long nowfilesize = file.length();
            URL url = new URL(urlc);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64;sx64; rv:54.0) Gecko/20100101 Firefox/54.0");
            connection.setRequestMethod("GET");

            connection.setRequestProperty("RANGE", "bytes=" + nowfilesize + "-");
            int filesize = connection.getContentLength();

            size = String.valueOf(filesize);
            name = filename;
            path = pathc;

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.seek(nowfilesize);

            InputStream inputStream = connection.getInputStream();
            int len = 0;
            byte[] buff = new byte[1024];
            int countnum = 0;
            while ((len = inputStream.read(buff)) != -1) {
                countnum++;
                if (countnum > 40) {
                    countnum = 0;
                    Progsslist.map.put(forid,((float)file.length() * 100) / filesize);
                }
                raf.write(buff, 0, len);
            }
            inputStream.close();
            raf.close();
            connection.disconnect();

        } catch (IOException e) {
            try {
                if (continuenum < 4) {
                    continuerepate(urlc, pathc,forid);
                }
            } catch (Exception e1) {

            }
        }
    }

    @PostMapping("/justdodownload")
    @ResponseBody
    public String justdodownload(String urlc,String pathc,String forid) throws Exception{
        justgorun(urlc,pathc,forid);
        return "OK";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
}

