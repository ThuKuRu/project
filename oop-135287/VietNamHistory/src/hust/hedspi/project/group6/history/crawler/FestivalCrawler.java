package hust.hedspi.project.group6.history.crawler;

import hust.hedspi.project.group6.history.common.Common;
import hust.hedspi.project.group6.history.model.Festival;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FestivalCrawler implements Crawler {

    private static String getDataClean(String data) {
        String array[] = data.split("(\\[)(\\b([0-9]|[1-9][0-9]|100)\\b)(\\])");
        String result = "";
        for (int i = 0; i < array.length; i++) {
            result += array[i];
        }
        return result;
    }

    public static String getDescriptionFromLink(String link) {
        try {
            Document document = Jsoup.connect(link).get();
            Elements elements = document.select("#mw-content-text > div.mw-parser-output > p");
            Elements elements1 = document.select("#mw-content-text > div.mw-parser-output > p.mw-empty-elt");
            String data;
            if (elements1.isEmpty()) {
                data = getDataClean(elements.get(0).text().trim());
            } else {
                data = getDataClean(elements.get(1).text().trim());
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void crawler() {
        try {
            String baseUrl = "https://vi.wikipedia.org/wiki/C%C3%A1c_ng%C3%A0y_l%E1%BB%85_%E1%BB%9F_Vi%E1%BB%87t_Nam";
            List<Map<String, Object>> festivalList = new ArrayList<Map<String, Object>>();
            Document document = Jsoup.connect(baseUrl).get();
            Document document1, document2;
            int i, j, k;
            for (i = 0; i < 3; i++) {
                Elements links = document.select(".wikitable").get(i).select("tr");
                for (j = 1; j < links.size(); j++) {
                    Festival festival = new Festival();
                    Map<String, Object> data = new HashMap<>();
                    if (i == 0) {
                        for (k = 0; k < 4; k++) {
                            if (k == 0) {
                                String name = getDataClean(links.get(j).child(k).text().trim());
                                data.put("id", festival.getId());
                                data.put("name", name);
                            } else if (k == 1) {
                                String organizeTime = getDataClean(links.get(j).child(k).text().trim());
                                data.put("organizeTime", organizeTime);
                            } else if (k == 2) {
                                String description = getDataClean(links.get(j).child(k).text().trim());
                                data.put("description", description);
                            } else {
                                data.put("location", "Cả nước");
                            }
                        }
                    } else if (i == 1) {
                        for (k = 0; k < 3; k++) {
                            if (k == 0) {
                                data.put("id", festival.getId());
                                String organizeTime = getDataClean(links.get(j).child(k).text().trim());
                                data.put("organizeTime", organizeTime);
                            } else if (k == 1) {
                                String name = getDataClean(links.get(j).child(k).text().trim());
                                data.put("name", name);
                                String linkWiki = "https://vi.wikipedia.org";
                                linkWiki += links.get(j).child(k).firstElementChild().attr("href");
                                document1 = Jsoup.connect(linkWiki).get();
                                Elements elements = document1.select("#ca-edit > a");
                                if (!elements.text().contains("Tạo mã nguồn")) {
                                    String description = getDescriptionFromLink(linkWiki);
                                    data.put("description", description);
                                } else {
                                    data.put("description", "Chưa có thông tin");
                                }
                            } else if (k == 2) {
                                String location = getDataClean(links.get(j).child(k).text().trim());
                                data.put("location", location);
                            }
                        }
                    } else {
                        for (k = 0; k < 2; k++) {
                            if (k == 0) {
                                data.put("id", festival.getId());
                                String organizeTime = getDataClean(links.get(j).child(k).text().trim());
                                data.put("organizeTime", organizeTime);
                                data.put("location", "Cả nước");
                            } else if (k == 1) {
                                String name = getDataClean(links.get(j).child(k).text().trim());
                                data.put("name", name);
                                String linkWiki = "https://vi.wikipedia.org";
                                linkWiki += links.get(j).child(k).firstElementChild().attr("href");
                                document2 = Jsoup.connect(linkWiki).get();
                                Elements elements = document2.select("#ca-edit > a");
                                if (!elements.text().contains("Tạo mã nguồn")) {
                                    String description = getDescriptionFromLink(linkWiki);
                                    data.put("description", description);
                                } else {
                                    data.put("description", "Chưa có thông tin");
                                }
                            }
                        }
                    }
                    festivalList.add(data);
                }
            }

            Common.saveToJson(festivalList, "festival.json");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

