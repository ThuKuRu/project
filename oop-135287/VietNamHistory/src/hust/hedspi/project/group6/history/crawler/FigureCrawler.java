package hust.hedspi.project.group6.history.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import hust.hedspi.project.group6.history.common.Common;
import hust.hedspi.project.group6.history.model.Figure;

public class FigureCrawler implements Crawler {

    private static String getDataClean(String data) {
        String arrayNumber[] = data.split("(\\[)(\\b([0-9]|[1-9][0-9]|[1-9][0-9][0-9]|1000)\\b)(\\])");
        String cutNumber = "";
        String result = "";
        for (int i = 0; i < arrayNumber.length; i++) {
            cutNumber += arrayNumber[i];
        }
        String arrayChar[] = cutNumber.split("(\\[)(\\b([a-zA-Z][a-zA-Z]|[a-zA-Z])\\b)(\\])");
        for (int i = 0; i < arrayChar.length; i++) {
            result += arrayChar[i];
        }
        return result;
    }

    public static void crawler() {
        try {
            ArrayList<Map<String, Object>> figureList = new ArrayList<Map<String, Object>>();
            String linkList = "https://vi.m.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam?fbclid=IwAR05K46QfGAvBKIe4X1EF8fjVGSTu8TYpaajrcz8OsSZ4oZyUXL6SNDJjS0";
            Document readList = Jsoup.connect(linkList).get();
            Elements listSection = readList.select("section");
            for (int k = 2; k <= 10; k++) {
                Elements listTable = listSection.get(k).select("table");
                Elements listDynasty = listSection.get(k).select("span[class=\"mw-headline\"]");
                for (int v = 0; v < listDynasty.size(); v++) {
                    String dynasty = getDataClean(listDynasty.get(v).text());
                    Elements listTr = null;
                    if (k < 6) {
                        Elements listCol = listTable.get(v).select("tr");
                        listTr = listCol;
                    } else if (k == 6) {
                        Elements listCol = listTable.get(v * 2 + 2).select("tr");
                        listTr = listCol;
                    } else if (k == 8 || k == 10) {
                        Elements listCol = listTable.get(v + 1).select("tr");
                        listTr = listCol;
                    } else if (k == 9) {
                        Elements listCol = listTable.get(v * 2 + 1).select("tr");
                        listTr = listCol;
                    } else if (k == 7) {
                        if (v == 0) {
                            Elements listCol = listTable.get(v + 1).select("tr");
                            listTr = listCol;
                        }
                        if (v == 1) {
                            Elements listTable1 = listTable.get(2).select("table");
                            Elements listCol = listTable1.get(0).select("tr");
                            listTr = listCol;
                        }
                    }
                    for (int w = 1; w < listTr.size(); w++) {
                        Elements listTd = listTr.get(w).select("td");
                        Map<String, Object> data = new HashMap<>();
                        if (listTd.size() == 10 || listTd.size() == 8 || listTd.size() == 9) {
                            String name = "",
                                    nienHieu = "",
                                    mienHieu = "",
                                    thuyHieu = "",
                                    tenHuy = "",
                                    theThu = "",
                                    year = "",
                                    description = "";
                            for (int j = 0; j < listTd.size(); j++) {

                                if (j == 1) {
                                    String linkWiki = "https://vi.wikipedia.org";
                                    Elements elements = listTd.get(j).select("a");
                                    linkWiki = linkWiki + elements.get(0).attr("href");
                                    description = getDescriptionFromLink(linkWiki);
                                    name = getDataClean(listTd.get(j).text());
                                } else if (j == 2) {
                                    mienHieu = getDataClean(listTd.get(j).text());
                                } else if (j == 3) {
                                    thuyHieu = getDataClean(listTd.get(j).text());
                                } else if (j == 4) {
                                    nienHieu = getDataClean(listTd.get(j).text());
                                } else if (j == 5) {
                                    tenHuy = getDataClean(listTd.get(j).text());
                                } else if (j == 6) {
                                    if (listTd.size() != 9) {
                                        theThu = getDataClean(listTd.get(j).text());
                                    } else {
                                        theThu = "không có";
                                    }
                                } else if (j == 7) {
                                    if (listTd.size() == 8) {
                                        year = getDataClean(listTd.get(j).text());
                                    } else if (listTd.size() == 10) {
                                        year = getDataClean(listTd.get(j).text() + listTd.get(j + 1).text() + listTd.get(j + 2).text());
                                    } else {
                                        year = getDataClean( listTd.get(j - 1).text() + listTd.get(j).text() + listTd.get(j + 1).text());
                                    }
                                }

                            }
                            Figure figure = new Figure();
                            data.put("id", figure.getId());
                            data.put("name", name);
                            data.put("description", description);
                            data.put("year", year);
                            data.put("dynasty", dynasty);
                            data.put("mienHieu", mienHieu);
                            data.put("thuyHieu", thuyHieu);
                            data.put("nienHieu", nienHieu);
                            data.put("tenHuy", tenHuy);
                            data.put("theThu", theThu);
                            figureList.add(data);
                        }
                    }
                }
            }
            Common.saveToJson(figureList, "figure.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getDescriptionFromLink(String link) throws IOException {
        Document doc = Jsoup.connect(link).get();
        Elements list = doc.select("div[class=\"mw-parser-output\"]");
        Elements listDepcription = list.select("p");
        if (listDepcription.size() >= 1) {
            String data = getDataClean(listDepcription.get(0).text());
            return data;
        } else if (listDepcription.size() == 2) {
            String data = getDataClean(listDepcription.get(1).text());
            return data;
        } else {
            return "không có dữ liệu";
        }
    }
}
