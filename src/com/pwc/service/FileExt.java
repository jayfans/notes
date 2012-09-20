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

    static String[] getLines(String filePath, int from) {
        return getLines(filePath, from, FileExt.getLineNumber(filePath) - 1);
    }

    static String[] getLines(String filePath, int from, int to) {

        if (from < 0 || to > FileExt.getLineNumber(filePath)) {
            return null;
        }

        String[] lines = new String[to - from + 1];

        int lineNumber = 0;
        FileReader fr = null;
        try {
            fr = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        BufferedReader br;
        if (fr != null) {
            br = new BufferedReader(fr);
            try {
                String currentLine;
                int count = 0;
                while ((currentLine = br.readLine()) != null) {
                    if (lineNumber <= to && lineNumber >= from) {
                        lines[count] = currentLine;
                        count++;
                    }
                    lineNumber++;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;//To change body of catch statement use File | Settings | File Templates.
            } finally {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;//To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
        return lines;
    }

    public static String copy(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) {
                InputStream inStream = new FileInputStream(oldPath);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("error  ");
            e.printStackTrace();
            return null;
        }
        return newPath;
    }

    public static void delete(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }
}


