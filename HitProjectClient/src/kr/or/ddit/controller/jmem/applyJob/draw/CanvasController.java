package kr.or.ddit.controller.jmem.applyJob.draw;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import ch.qos.logback.core.pattern.color.WhiteCompositeConverter;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import kr.or.ddit.common.ShowAlert;
import kr.or.ddit.controller.creq.CreqBoardInnerController;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.jobmem.JobMemberVO;
import kr.or.ddit.myApply.MyApplyVO;
import kr.or.ddit.rmi.client.ClientConnectFactory;
import kr.or.ddit.rmi.interf.IRemote;
import kr.or.ddit.test.TestVO;

public class CanvasController implements Initializable {

	@FXML
	Pane canvasPane;
	@FXML
	Button submit, reset;
	@FXML
	GridPane gridPane;
	@FXML
	TextArea testconarea;
	
	private Canvas canvas;
	
	private GraphicsContext gc;
	
	private ColorPicker cp;
	
	private Slider slider;
	
	private JobMemberVO sessionMem;
	
	
	
	private IRemote conn;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conn = ClientConnectFactory.getClientConnect();
		sessionMem = LoginController.ssessionJMem;
		canvasPane.setPrefSize(1000, 530);
		MyApplyVO vo = new MyApplyVO();
		vo.setCor_name(CreqBoardInnerController.selCorname);
		int test_no = conn.getIMyApplyService().selectTestNo(vo);
		
		TestVO testvo = conn.getITestService().selectTestInfo(test_no);
		testconarea.setText(testvo.getTest_content());
		
		drawCanvas();
		addTools();
	}
	
	private void addTools() {
		cp = new ColorPicker();
		slider = new Slider();
		
		cp.setValue(Color.BLACK);
		cp.setOnAction(e->{
			gc.setStroke(cp.getValue());
		});
		
		slider.setMin(1);
		slider.setMax(100);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.valueProperty().addListener(e -> {
			double value = slider.getValue();
			String str = String.format("%.1f", value);
			gc.setLineWidth(value);
		});

		gridPane.addRow(0, cp, slider);
	}
	private void drawCanvas() {
		canvas = new Canvas(canvasPane.getPrefWidth(), canvasPane.getPrefHeight());
//		GraphicsContext gc;
		
		gc = canvas.getGraphicsContext2D();

		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);

		canvasPane.setStyle("-fx-background-color: #FFFFFF;");
		canvasPane.getChildren().addAll(canvas);

		canvasPane.setOnMousePressed(e -> {
			gc.beginPath();
			gc.lineTo(e.getX(), e.getY());
			gc.stroke();
		});

		canvasPane.setOnMouseDragged(e -> {
			gc.lineTo(e.getX(), e.getY());
			gc.stroke();
		});

	}
	
	@FXML
	public void resetCanvas(ActionEvent event) {
		canvasPane.getChildren().remove(canvas);
		cp.setValue(Color.BLACK);
		slider.setValue(1);
		drawCanvas();
	}

	@FXML
	public void submitCanvas(ActionEvent event) {
		File file2 = new File("");
		String path = file2.getAbsolutePath();
		File file = new File(path+"\\src\\kr\\or\\ddit\\canimg\\"+sessionMem.getMem_id()+".png");
		SnapshotParameters parameters = new SnapshotParameters();
		parameters.setFill(Color.WHITE);
		WritableImage writeImage = new WritableImage((int) canvasPane.getPrefWidth(),
				(int) canvasPane.getPrefHeight() / 1);
		writeImage = canvas.snapshot(parameters, writeImage);

		BufferedImage bufImageARGB = SwingFXUtils.fromFXImage(writeImage, null);
		BufferedImage bufImageRGB = new BufferedImage(bufImageARGB.getWidth(), bufImageARGB.getHeight(),
				BufferedImage.OPAQUE);

		Graphics2D graphics = bufImageRGB.createGraphics();
		graphics.drawImage(bufImageARGB, 0, 0, null);
		try {
			ImageIO.write(bufImageRGB, "png", file);
			System.out.println(file.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		graphics.dispose();
		
		MyApplyVO vo = new MyApplyVO();
		
		vo.setCor_name(CreqBoardInnerController.selCorname);
		int test_no = conn.getIMyApplyService().selectTestNo(vo);
		vo.setRes_state("진행중");
		vo.setJmem_id(LoginController.ssessionJMem.getMem_id());
		vo.setSource(file.getAbsolutePath());
		vo.setTest_no(test_no);
		
		boolean result = conn.getIMyApplyService().insertApply(vo);
		if(result==true){
			ShowAlert.showAlertINFORMATION("갑사합니다", "구직신청에 성공하였습니다.");
			Stage resultStage = (Stage) ((Node)canvasPane).getScene().getWindow();
			resultStage.close();
		}else {
			ShowAlert.showAlertError("죄송합니다", "구직신청에 실패하였습니다.");
		}

	}

}


