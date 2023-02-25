package hust.hedspi.project.group6;

import hust.hedspi.project.group6.history.crawler.*;
import hust.hedspi.project.group6.history.screen.Home;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class app {
    public static void main(String[] args) {
        // crawl data
        if (!checkFileEmpty("dynasty.json")) {
            DynastyCrawler.crawler();
            System.out.println("Lay du lieu ve thoi dai thanh cong!");
        } else {
            System.out.println("Da co du lieu ve thoi dai");
        }
        if (!checkFileEmpty("event.json")) {
            EventCrawler.crawler();
            System.out.println("Lay du lieu ve sư kien thanh cong!");
        } else {
            System.out.println("Da co du lieu ve su kien");
        }
        if (!checkFileEmpty("festival.json")) {
            FestivalCrawler.crawler();
            System.out.println("Lay du lieu ve le hoi thanh cong!");
        } else {
            System.out.println("Da co du lieu le hoi");
        }
        if (!checkFileEmpty("figure.json")) {
            FigureCrawler.crawler();
            System.out.println("Lay du lieu ve nhan vat lich su thanh cong!");
        } else {
            System.out.println("Da co du lieu nhan vat lich su");
        }
        if (!checkFileEmpty("historicalSite.json")) {
            HistoricalSiteCrawler.crawler();
            System.out.println("Lay du lieu ve di tich lich su thanh cong!");
        } else {
            System.out.println("Da co du lieu di tich lich su");
        }

        Home.mainHome(args);
    }

    private static boolean checkFileEmpty(String fileName) {
        try {
            String root = System.getProperty("user.dir");
            String path = root + "/json/" + fileName ;
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            if (myReader.hasNextLine()) {
                myReader.close();
                return true;
            } else {
                myReader.close();
                return false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
