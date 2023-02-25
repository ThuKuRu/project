package hust.hedspi.project.group6.history.crawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import hust.hedspi.project.group6.history.common.Common;
import hust.hedspi.project.group6.history.model.HistoricalSite;

public class HistoricalSiteCrawler implements Crawler {

    public static void crawler() {
        try {
            String link = "http://dsvh.gov.vn/danh-muc-di-tich-quoc-gia-dac-biet-1752?fbclid=IwAR03TM6Fgsn3CA3Ua_puXkOr3WfWpoceu02mNmKHnCGmNM89OiAuFti8u_c";
            Document doc;
            doc = Jsoup.connect(link).get();
            ArrayList<Map<String, Object>> historyList = new ArrayList<Map<String, Object>>();
            Elements list = doc.select("tr");
            for (int i = 1; i < list.size(); i++) {
                Elements listTd = list.get(i).select("td");
                Map<String, Object> data = new HashMap<String, Object>();
                String name = "", description = "", place = "";
                for (int j = 0; j < listTd.size(); j++) {
                    if (j == 1) {
                        name = listTd.get(j).text();
                        String href = listTd.get(j).firstElementChild().firstElementChild().attr("href");
                        if (href != "") {
                            String linkGetHistoricalDescription = "http://dsvh.gov.vn" + href.replace("http:/", "");
                            Document docDes = Jsoup.connect(linkGetHistoricalDescription).get();
                            Elements listDes = docDes.select("p");
                            description = listDes.get(0).text();
                        } else {
                            description = "Khong co";
                        }
                    }
                    if (j == 3) {
                        place = listTd.get(j).text();
                    }
                }
                HistoricalSite hSite = new HistoricalSite();
                data.put("id", hSite.getId());
                data.put("name", name);
                data.put("description", description);
                data.put("place", place);
                historyList.add(data);
            }
            Common.saveToJson(historyList, "historicalSite.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
