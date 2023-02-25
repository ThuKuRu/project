package hust.hedspi.project.group6.history.screen;

import java.io.FileReader;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import hust.hedspi.project.group6.history.model.Dynasty;
import hust.hedspi.project.group6.history.model.DynastyChild;
import hust.hedspi.project.group6.history.model.Event;
import hust.hedspi.project.group6.history.model.Festival;
import hust.hedspi.project.group6.history.model.Figure;
import hust.hedspi.project.group6.history.model.HistoricalSite;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class HomeController implements Initializable {

    @FXML
    private Label entity;
    
    @FXML
    private TextField searchBox;

    @FXML
    private Text name;

    @FXML
    private Text time;

    @FXML
    private Text description;

    @FXML
    private Text place;

    @FXML
    private Text more;

    @FXML
    private Text ref1;

    @FXML
    private Text ref2;

    @FXML
    private Text ref3;

    @FXML
    private Text ref4;

    @FXML
    private ListView<Hyperlink> selectionList;
    
    ListView<Hyperlink> originSelectionList = new ListView<Hyperlink>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.init();
    }

    public void init() {
        try {
            Gson gson = new Gson();
            String root = System.getProperty("user.dir");
            String path = root + "/json/dynasty.json";
            FileReader reader = new FileReader(path);
            List<Dynasty> dynastyList = gson.fromJson(reader, new TypeToken<List<Dynasty>>(){}.getType());

            this.entity.setText("TRIỀU ĐẠI");
            this.searchBox.setPromptText("Tìm kiếm Triều đại");
            this.searchBox.setText("");

            Dynasty first = dynastyList.get(0);
            this.name.setText(first.getName());
            this.time.setText("Thời gian: " + first.getTime());
            this.description.setText("Mô tả: " + first.getDescription());
            this.place.setText("");
            this.more.setText("");
            this.ref1.setText("");
            this.ref2.setText("");
            this.ref3.setText("");
            this.ref4.setText("");

            this.selectionList.getItems().clear();
            for (Dynasty dynasty : dynastyList) {
                Hyperlink link = new Hyperlink(dynasty.getName());
                link.setOnAction(e -> {
                    this.name.setText(dynasty.getName());
                    this.time.setText("Thời gian: " + dynasty.getTime());
                    this.description.setText("Mô tả: " + dynasty.getDescription());
                    this.place.setText("");
            		this.more.setText("");
                    this.ref1.setText("");
                    this.ref2.setText("");
                    this.ref3.setText("");
                    this.ref4.setText("");
                });
                this.selectionList.getItems().add(link);

                for (DynastyChild dynastyChild : dynasty.getDynasty()) {
                    Hyperlink linkChild = new Hyperlink("      " + dynastyChild.getName());
                    linkChild.setOnAction(e -> {
                        this.name.setText(dynastyChild.getName());
                        this.time.setText("Thời gian: " + dynastyChild.getTime());
                        this.description.setText("Mô tả: " + dynastyChild.getDescription());
                    });
                    this.selectionList.getItems().add(linkChild);
                }
            }
            
            this.originSelectionList.getItems().clear();
            ObservableList<Hyperlink> tmp = this.selectionList.getItems();
            this.originSelectionList.getItems().addAll(tmp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	@FXML
	void btnDynastyPressed (ActionEvent event) {
		/* vì màn hình ban đầu là triều đại nên khi ấn vào nút "TRIỀU ĐẠI"
		 * sẽ xử lý giống với khởi tạo ban đầu
		 */
		this.init();
	}
	
	@FXML
	void btnFigurePressed (ActionEvent event) {
		try {
			Gson gson = new Gson();
			String root = System.getProperty("user.dir");
	        String path = root + "/json/figure.json";
            FileReader reader = new FileReader(path);
            List<Figure> figureList = gson.fromJson(reader, new TypeToken<List<Figure>>(){}.getType());

			this.entity.setText("NHÂN VẬT LỊCH SỬ");
			this.searchBox.setPromptText("Tìm kiếm Nhân vật lịch sử");
            this.searchBox.setText("");

			Figure first = figureList.get(0);
			this.name.setText(first.getName());
			this.time.setText("Năm sinh - năm mất: " + first.getYear());
			this.description.setText(first.getDescription());
			this.place.setText("");
			this.more.setText("Niên Hiệu: " + first.getNienHieu() + "\n"
							 + "Miến Hiệu: " + first.getMienHieu() + "\n"
							 + "Thụy Hiệu: " + first.getThuyHieu() + "\n"
							 + "Thế Thứ: " + first.getTheThu() + "\n"
							 + "Tên Húy: "+ first.getTenHuy() + "\n"
			);
			this.ref1.setText("Thời Kỳ / Triều Đại: " + first.getDynasty());
    		this.ref2.setText("");
    		this.ref3.setText("");
    		this.ref4.setText("");
    		
			this.selectionList.getItems().clear();
            for (Figure figure : figureList) {
            	Hyperlink link = new Hyperlink(figure.getName());
            	link.setOnAction(e -> {
            		this.name.setText(figure.getName());
            		this.time.setText("Năm sinh - năm mất: " + figure.getYear());
            		this.description.setText(figure.getDescription());
					this.place.setText("");
            		this.more.setText("Niên Hiệu: " + figure.getNienHieu() + "\n"
									+ "Miến Hiệu: " + figure.getMienHieu() + "\n"
									+ "Thụy Hiệu: " + figure.getThuyHieu() + "\n"
									+ "Thế Thứ: " + figure.getTheThu() + "\n"
									+ "Tên Húy: " + figure.getTenHuy() + "\n"
					);
            		this.ref1.setText("Thời Kỳ / Triều Đại: " + figure.getDynasty());
            		this.ref2.setText("");
            		this.ref3.setText("");
            		this.ref4.setText("");
            	});
            	this.selectionList.getItems().add(link);
            }
            
            this.originSelectionList.getItems().clear();
            ObservableList<Hyperlink> tmp = this.selectionList.getItems();
            this.originSelectionList.getItems().addAll(tmp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void btnEventPressed (ActionEvent event) {
    	try {
            Gson gson = new Gson();
            String root = System.getProperty("user.dir");
            String path = root + "/json/event.json";
            FileReader reader = new FileReader(path);
            List<Event> eventList = gson.fromJson(reader, new TypeToken<List<Event>>(){}.getType());

            this.entity.setText("SỰ KIỆN LỊCH SỬ");
            this.searchBox.setPromptText("Tìm kiếm Sự kiện lịch sử");
            this.searchBox.setText("");

            Event first = eventList.get(0);
            this.name.setText(first.getName());
            this.time.setText("Thời gian: " + first.getOrganizeTime());
            this.description.setText(first.getDescription());
            this.place.setText("");
    		this.more.setText("Quân ta: " + first.getQuanTa() + "\n"
    						+ "Quân địch: " + first.getQuanDich() + "\n"
    						+ "Kết quả: " + first.getKetQua());
            this.ref1.setText("Nhân vật liên quan: "+ first.getRelatedFigure().toString()
    				.replaceAll("\\[", "").replaceAll("\\]",""));
            this.ref2.setText("");
            this.ref3.setText("");
            this.ref4.setText("");

            this.selectionList.getItems().clear();
            for (Event events : eventList) {
                Hyperlink link = new Hyperlink(events.getName());
                link.setOnAction(e -> {
                    this.name.setText(events.getName());
            		this.time.setText("Thời gian: " + events.getOrganizeTime());
                    this.description.setText(events.getDescription());
                    this.place.setText("");
            		this.more.setText("Quân ta: " + events.getQuanTa() + "\n"
    						+ "Quân địch: " + events.getQuanDich() + "\n"
    						+ "Kết quả: " + events.getKetQua());
                    this.ref1.setText("Nhân vật liên quan: "+ events.getRelatedFigure().toString()
            				.replaceAll("\\[", "").replaceAll("\\]",""));
                    this.ref2.setText("");
                    this.ref3.setText("");
                    this.ref4.setText("");
                });
                this.selectionList.getItems().add(link);
            }
            
            this.originSelectionList.getItems().clear();
            ObservableList<Hyperlink> tmp = this.selectionList.getItems();
            this.originSelectionList.getItems().addAll(tmp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnHistoricalSitePressed(ActionEvent event) {
        try {
            Gson gson = new Gson();
            String root = System.getProperty("user.dir");
            String path = root + "/json/historicalSite.json";
            FileReader reader = new FileReader(path);
            List<HistoricalSite> hSiteList = gson.fromJson(reader, new TypeToken<List<HistoricalSite>>(){}.getType());

            this.entity.setText("DI TÍCH LỊCH SỬ");
            this.searchBox.setPromptText("Tìm kiếm Di tích lịch sử");
            this.searchBox.setText("");

            HistoricalSite first = hSiteList.get(0);
            this.name.setText(first.getName());
            this.time.setText("");
            this.description.setText(first.getDescription());
            this.place.setText("Địa điểm: " + first.getPlace());
            this.more.setText("");
            this.ref1.setText("");
            this.ref2.setText("");
            this.ref3.setText("");
            this.ref4.setText("");

            this.selectionList.getItems().clear();
            for (HistoricalSite hSite : hSiteList) {
                Hyperlink link = new Hyperlink(hSite.getName());
                link.setOnAction(e -> {
                    this.name.setText(hSite.getName());
//            		this.time.setText("");
                    this.description.setText(hSite.getDescription());
                    this.place.setText("Địa điểm: " + hSite.getPlace());
//            		this.more.setText("");
                    this.ref1.setText("");
                    this.ref2.setText("");
                    this.ref3.setText("");
                    this.ref4.setText("");
                });
                this.selectionList.getItems().add(link);
            }
            
            this.originSelectionList.getItems().clear();
            ObservableList<Hyperlink> tmp = this.selectionList.getItems();
            this.originSelectionList.getItems().addAll(tmp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void btnFestivalPressed (ActionEvent event) {
		try {
			Gson gson = new Gson();
			String root = System.getProperty("user.dir");
			String path = root + "/json/festival.json";
			FileReader reader = new FileReader(path);
			List<Festival> festivalList = gson.fromJson(reader, new TypeToken<List<Festival>>(){}.getType());

			this.entity.setText("LỄ HỘI");
			this.searchBox.setPromptText("Tìm kiếm Lễ hội");
            this.searchBox.setText("");

			Festival festival = festivalList.get(0);
			this.name.setText(festival.getName());
			this.description.setText(festival.getDescription());
			this.place.setText("Địa điểm: " + festival.getLocation());
			this.time.setText("Thời gian: " + festival.getOrganizeTime());
			this.more.setText("");
            this.ref1.setText("");
            this.ref2.setText("");
            this.ref3.setText("");
            this.ref4.setText("");

			this.selectionList.getItems().clear();
			for(Festival fes : festivalList) {
				Hyperlink hyperlink = new Hyperlink(fes.getName());
				hyperlink.setOnAction(e -> {
					this.name.setText(fes.getName());
					this.description.setText(fes.getDescription());
					this.place.setText("Địa điểm: " + fes.getLocation());
					this.time.setText("Thời gian: " + fes.getOrganizeTime());
                    this.more.setText("");
                    this.ref1.setText("");
                    this.ref2.setText("");
                    this.ref3.setText("");
                    this.ref4.setText("");
                });
				this.selectionList.getItems().add(hyperlink);
			}

			this.originSelectionList.getItems().clear();
			ObservableList<Hyperlink> tmp = this.selectionList.getItems();
			this.originSelectionList.getItems().addAll(tmp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void inputSearch(ActionEvent event) {
		String searchWord = this.searchBox.getText().toLowerCase();
		this.selectionList.getItems().clear();
		this.selectionList.getItems().addAll(search(searchWord));
	}
	
	private FilteredList<Hyperlink> search(String searchWord){
		ObservableList<Hyperlink> links = this.originSelectionList.getItems();
		FilteredList<Hyperlink> filteredLinks = new FilteredList<>(links);
		filteredLinks.setPredicate(link -> {
			if (searchWord == null || searchWord.isEmpty()) {
				return true;
			}
			return link.getText().toLowerCase().contains(searchWord);
		});
		return filteredLinks;
	}
}
