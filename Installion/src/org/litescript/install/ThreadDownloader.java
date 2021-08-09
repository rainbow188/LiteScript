package org.litescript.install;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * 多线程下载
 *
 * @author jam
 */
public class ThreadDownloader {

    public static int size = 0;
    public static int hasDownload = 0;

    public static String download() throws IOException, InterruptedException {
        long begin_time = new Date().getTime();
        URL url = new URL("https://github.com/rainbow188/LiteScript/blob/function/win32/LiteSciptVM.exe");
        URLConnection conn = url.openConnection();
        String fileName = url.getFile();
        fileName = fileName.substring(fileName.lastIndexOf("/"));
        int fileSize = conn.getContentLength();
        size = fileSize;
        int blockSize = 1024 * 1024;
        int blockNum = fileSize / blockSize;
        if ((fileSize % blockSize) != 0) {
            blockNum += 1;
        }
        Thread[] threads = new Thread[blockNum];
        for (int i = 0; i < blockNum; i++) {
            hasDownload = (blockNum * i * blockSize) / fileSize;
            final int index = i;
            final int finalBlockNum = blockNum;
            final String finalFileName = fileName;
            threads[i] = new Thread() {
                public void run() {
                    try {
                        URLConnection conn = url.openConnection();
                        InputStream in = conn.getInputStream();
                        int beginPoint = 0, endPoint = 0;
                        beginPoint = index * blockSize;
                        if (index < finalBlockNum - 1) {
                            endPoint = beginPoint + blockSize;
                        } else {
                            endPoint = fileSize;
                        }

                        File filePath = new File("C:" + File.separator + "Program Files (x86)" + File.separator + "LiteScript" + File.separator);
                        if (!filePath.exists()) {
                            filePath.mkdirs();
                        }
                        File file = new File(filePath, finalFileName + "_" + (index + 1));
                        if (!file.exists()) file.createNewFile();
                        FileOutputStream fos = new FileOutputStream(file);

                        in.skip(beginPoint);
                        byte[] buffer = new byte[1024];
                        int count;
                        int process = beginPoint;
                        while (process < endPoint) {

                            count = in.read(buffer);
                            if (process + count >= endPoint) {
                                count = endPoint - process;
                                process = endPoint;
                            } else {
                                process += count;
                            }
                            fos.write(buffer, 0, count);

                        }
                        fos.close();
                        in.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            };
            threads[i].start();

        }

        for (Thread t : threads) {
            t.join();
        }

        // 若该文件夹不存在，则创建一个文件夹
        File filePath = new File("C:" + File.separator + "Program Files (x86)" + File.separator + "LiteScript" + File.separator + "bin");
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        // 定义文件输出流
        FileOutputStream fos = new FileOutputStream("C:" + File.separator + "Program Files (x86)" + File.separator + "LiteScript" + File.separator + "bin" + File.separator + fileName);
        for (int i = 0; i < blockNum; i++) {
            FileInputStream fis = new FileInputStream("C:" + File.separator + "Program Files (x86)" + File.separator + "LiteScript" + File.separator + "bin" + File.separator + fileName + "_" + (i + 1));
            byte[] buffer = new byte[1024];
            int count;
            while ((count = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, count);
            }
            fis.close();
        }
        fos.close();

        long end_time = new Date().getTime();
        long seconds = (end_time - begin_time) / 1000;
        long minutes = seconds / 60;
        long second = seconds % 60;
        return "下载完成 用时" + minutes + "分" + second + "秒";
    }

}