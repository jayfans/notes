package com.pwc.service;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: zhixiong
 * Date: 9/15/12
 * Time: 6:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileExt {
    static String readLastLine(String filePath, String charset) throws IOException {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(filePath, "r");
            long len = raf.length();
            if (len == 0L) {
                return "";
            } else {
                long pos = len - 1;
                while (pos > 0) {
                    pos--;
                    raf.seek(pos);
                    if (raf.readByte() == '\n') {
                        break;
                    }
                }
                if (pos == 0) {
                    raf.seek(0);
                }
                byte[] bytes = new byte[(int) (len - pos)];
                raf.read(bytes);
                if (charset == null) {
                    return new String(bytes);
                } else {
                    return new String(bytes, charset);
                }
            }
        } catch (FileNotFoundException e) {
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (Exception e2) {
                }
            }
        }
        return null;
    }

    static int getLineNumber(String filePath) {
        int count = 0;
        FileReader fr = null;
        try {
            fr = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br;

        if (fr != null) {
            br = new BufferedReader(fr);
            try {
                while (br.readLine() != null) {
                    count++;
                }
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } finally {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
        return count;
    }

    static void appendLine(String filePath, String content) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(filePath, true));
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        try {
            bw.write(content);
            bw.newLine();
            bw.flush();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
