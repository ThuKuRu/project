package hust.hedspi.project.group6.history.crawler;

import hust.hedspi.project.group6.history.model.Figure;
import org.jsoup.Jsoup;
import hust.hedspi.project.group6.history.common.Common;
import hust.hedspi.project.group6.history.model.Event;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class EventCrawler implements Crawler{

    private static String getDescriptionFromLink(String link) throws IOException {
    	try {
	        Document doc = Jsoup.connect(link).get();
	        Elements list = doc.select("#mw-content-text > div.mw-parser-output > p");
	        Elements listDepcription = list.select("p");
	        return listDepcription.get(0).text();
    	} catch (Exception e) {
    		throw e;
    	}
    }

    public static void crawler() {
        try {
            String baseUrl = "https://vi.wikipedia.org/wiki/C%C3%A1c_cu%E1%BB%99c_chi%E1%BA%BFn_tranh_Vi%E1%BB%87t_Nam_tham_gia?fbclid=IwAR3rMUjHC6-4E27cyMdvYiGGCRNcYv8mfsB7Lln3lCU6ywsoLRacEdV9rTM";
            ArrayList<Map<String, Object>> eventList = new ArrayList<Map<String, Object>>();
            Document doc;
            Document document1;
            doc = Jsoup.connect(baseUrl).get();
            Elements links = doc.select("div[class=\"mw-parser-output\"]");
            Elements eventLinks = links.get(0).select("table[class=\"wikitable\"]");
            List<Figure> relatedFigure = new ArrayList<>();
            for (int i = 0; i < eventLinks.size(); i++) {
                Elements eventTr = eventLinks.get(i).select("Tr");
                for (int k = 1; k < eventTr.size(); k++) {
                    Elements eventTd = eventTr.get(k).select("Td");
                    Map<String, Object> data = new HashMap<>();
                    String name = "",
                            organizeTime = "",
                            quanTa = "",
                            quanDich = "",
                            ketQua = "",
                            description = "";
                    if(eventTd.size()==4) {
                        for (int j = 0; j < eventTd.size(); j++) {
                            if (j == 0) {
                                if (i!=0||k!=1) {
                                    String linkWiki = "https://vi.wikipedia.org";
                                    Elements elements = eventTd.get(j).select("a");
                                    if(elements.size()!=0) {
                                        linkWiki += elements.get(0).attr("href");
                                        document1 = Jsoup.connect(linkWiki).get();
                                        Elements elements1 = document1.select("#ca-edit > a");
                                        if (!elements1.text().contains("Tạo mã nguồn")) {
                                            description = getDescriptionFromLink(linkWiki);
                                            data.put("description", description);
                                        } else {
                                            data.put("description", "Chưa có thông tin");
                                        }
                                    } else {
                                        data.put("description","Chưa có thông tin");
                                    }
                                } else {
                                    data.put("description","Chưa có thông tin");
                                }

                                if (eventTd.get(j).text().contains("(")) {
                                    String output1[] = eventTd.get(j).text().split("\\(");
                                    name = output1[0];
                                    String[] output2 = output1[1].split("\\)"); //output2 = Time with ")"
                                    organizeTime = output2[0];
                                }

                            } else if (j==1){
                                quanTa = eventTd.get(j).text();
                            } else if (j==2) {
                                quanDich = eventTd.get(j).text();
                            } else {
                                ketQua = eventTd.get(j).text();
                            }
                        }
                        Event event = new Event();
                        data.put("id", event.getId());
                        data.put("name", name);
                        data.put("organizeTime", organizeTime);
                        data.put("quanTa", quanTa);
                        data.put("quanDich", quanDich);
                        data.put("ketQua", ketQua);
                        data.put("relatedFigure",relatedFigure);
                    }

                    if(!data.isEmpty()) {
                        eventList.add(data);
                    }
                }
            }
            Common.saveToJson(eventList, "event.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
