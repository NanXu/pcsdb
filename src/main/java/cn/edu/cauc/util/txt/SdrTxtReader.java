package cn.edu.cauc.util.txt;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.aspectj.util.FileUtil;

import java.io.File;
import java.io.IOException;

/**
 * @author: Nan.Xu
 * @create: 2018-11-24
 **/
public class SdrTxtReader {

    public static void read(String path) {
        File txtFile = new File(path);
        LineIterator lineIterator = null;
        try {
            lineIterator = FileUtils.lineIterator(txtFile, "UTF-8");
            while (lineIterator.hasNext()) {//每一行
                String line = lineIterator.nextLine();
                String[] arrLine = line.split("\t");
                for (String str : arrLine) {
                    System.out.print(str+" ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            LineIterator.closeQuietly(lineIterator);
        }
    }

    public static void main(String[] args) {
        String path = "/Users/xunan/Documents/Daily/sdr-Tab Delimited Data/sdr1997g.txt";
        SdrTxtReader.read(path);
    }
}
