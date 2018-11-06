package kr.or.ddit.controller.signup;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import kr.or.ddit.common.ZipVO;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.controller.signup.CorRegnoController.CorZip;
import kr.or.ddit.controller.signup.ZipDrawerController.Zip;
import kr.or.ddit.corRegno.CorRegnoVO;
import kr.or.ddit.rmi.interf.IRemote;

public class CorRegnoController implements Initializable {
	@FXML
	private JFXButton btnCheck;

	@FXML
	private JFXButton btnCancel;

	@FXML
	private JFXButton btnSearch;

	@FXML
	private JFXTextField searchField;
	
	@FXML
	private JFXTreeTableView<CorZip> corZipTable;
	
	@FXML
	private TreeTableColumn<CorZip, String> cor_addr;

	@FXML
	private TreeTableColumn<CorZip, String> cor_tel;

	@FXML
	private TreeTableColumn<CorZip, String> cor_post;

	@FXML
	private TreeTableColumn<CorZip, String> cor_name;

	@FXML
	private TreeTableColumn<CorZip, String> cor_no;
	
	@FXML
	private TreeTableColumn<CorZip, String> cor_ceo;
	
	@FXML
	private JFXComboBox<String> comboSelect;
	
	private IRemote conn;
	
	public static String selCorRegno;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conn = LoginController.conn;
		btnSearch.setOnAction(event -> zipSearch());
		setTable();
	}
	
	private void setTable() {
		comboSelect.getItems().addAll("사업자등록번호","기업 이름");
		comboSelect.setValue("사업자등록번호");
	}
	
	private void zipSearch() {
		cor_no = new JFXTreeTableColumn<>("사업자 번호");
		cor_no.setPrefWidth(94);
		cor_name = new JFXTreeTableColumn<>("이름");
		cor_name.setPrefWidth(131);
		cor_tel = new JFXTreeTableColumn<>("전화번호");
		cor_tel.setPrefWidth(119);
		cor_addr = new JFXTreeTableColumn<>("주소");
		cor_addr.setPrefWidth(91);
		cor_post = new JFXTreeTableColumn<>("우편번호");
		cor_post.setPrefWidth(91);
		cor_ceo = new JFXTreeTableColumn<>("사업자명");
		cor_ceo.setPrefWidth(91);
		
		cor_no.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<CorZip, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<CorZip, String> param) {
						return param.getValue().getValue().cor_no;
					}
				});

		cor_name.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<CorZip, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<CorZip, String> param) {
						return param.getValue().getValue().cor_name;
					}
				});

		cor_tel.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<CorZip, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<CorZip, String> param) {
						return param.getValue().getValue().cor_tel;
					}
				});

		cor_addr.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<CorZip, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<CorZip, String> param) {
						return param.getValue().getValue().cor_addr;
					}
				});
		
		cor_post.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CorZip,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CorZip, String> param) {
				return param.getValue().getValue().cor_post;
			}
		});
		
		cor_ceo.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CorZip,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<CorZip, String> param) {
				return param.getValue().getValue().cor_ceo;
			}
		});
		
		if (searchField.getText().isEmpty()) {
			return;
		}	     
		
		HashMap<String, String> corRegnoMap = new HashMap<>();
		String comboValue = comboSelect.getValue();
		List<CorRegnoVO> list = null;
		ObservableList<CorZip> zipdata = FXCollections.observableArrayList();
		
		if (comboValue.equals("사업자등록번호")) {
			corRegnoMap.put("cor_no", searchField.getText());
			list = conn.getIRegnoService().selectCorRegno();
		
		} else if (comboValue.equals("기업 이름")) {
			corRegnoMap.put("cor_name", searchField.getText());
			list = conn.getIRegnoService().searchCorName(corRegnoMap);
		}
		
		for (int i = 0; i < list.size(); i++) {
			CorZip zip = new CorZip(list.get(i).getCor_no(), 
					list.get(i).getCor_name(), 
					list.get(i).getCor_ceo(),
					list.get(i).getCor_tel(),
					list.get(i).getCor_post(),
					list.get(i).getCor_addr());
			zipdata.add(zip);
		}
		
		final TreeItem<CorZip> root = new RecursiveTreeItem<CorZip>(zipdata, RecursiveTreeObject::getChildren);
		corZipTable.getColumns().setAll(cor_no, cor_name,cor_ceo, cor_post, cor_addr, cor_tel);
		corZipTable.setRoot(root);
		corZipTable.setShowRoot(false);
	}


	class CorZip extends RecursiveTreeObject<CorZip> {
		StringProperty cor_no;
		StringProperty cor_name;
		StringProperty cor_ceo;
		StringProperty cor_tel;
		StringProperty cor_post;
		StringProperty cor_addr;

		public CorZip(String cor_no, 
				String cor_name, 
				String cor_ceo, 
				String cor_tel, 
				String cor_post,
				String cor_addr) {
			this.cor_no = new SimpleStringProperty(cor_no);
			this.cor_name = new SimpleStringProperty(cor_name);
			this.cor_ceo = new SimpleStringProperty(cor_ceo);
			this.cor_tel = new SimpleStringProperty(cor_tel);
			this.cor_post = new SimpleStringProperty(cor_post);
			this.cor_addr = new SimpleStringProperty(cor_addr);
		}

		public StringProperty getCor_no() {
			return cor_no;
		}

		public void setCor_no(StringProperty cor_no) {
			this.cor_no = cor_no;
		}

		public StringProperty getCor_name() {
			return cor_name;
		}

		public void setCor_name(StringProperty cor_name) {
			this.cor_name = cor_name;
		}

		public StringProperty getCor_ceo() {
			return cor_ceo;
		}

		public void setCor_ceo(StringProperty cor_ceo) {
			this.cor_ceo = cor_ceo;
		}

		public StringProperty getCor_tel() {
			return cor_tel;
		}

		public void setCor_tel(StringProperty cor_tel) {
			this.cor_tel = cor_tel;
		}

		public StringProperty getCor_post() {
			return cor_post;
		}

		public void setCor_post(StringProperty cor_post) {
			this.cor_post = cor_post;
		}

		public StringProperty getCor_addr() {
			return cor_addr;
		}

		public void setCor_addr(StringProperty cor_addr) {
			this.cor_addr = cor_addr;
		}
	}
}



