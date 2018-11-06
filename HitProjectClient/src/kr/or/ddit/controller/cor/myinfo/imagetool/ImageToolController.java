package kr.or.ddit.controller.cor.myinfo.imagetool;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.cormem.CorMemberVO;
import kr.or.ddit.rmi.client.ClientConnectFactory;
import kr.or.ddit.rmi.interf.IRemote;

public class ImageToolController implements Initializable {
	
	@FXML
	private ImageView imgview;
	
	@FXML
	private Text source, target;
	
	@FXML
	private AnchorPane anchor;
	
	@FXML
	private JFXRadioButton profileradio,logoradio;
	@FXML
	private Group imageLayer;
	@FXML
	private ToggleGroup imgtype;
	
	@FXML JFXTextArea manualarea;
	
	public static Image img;
	
	RubberBandSelection rubberBandSelection;
	
	private List<File> files;
	
	
	private IRemote conn;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conn = ClientConnectFactory.getClientConnect();
		profileradio.setUserData("1");
		logoradio.setUserData("2");
		rubberBandSelection = new RubberBandSelection(imageLayer);
		files = new ArrayList<>();
		setManual();
		MenuItem Menu = new MenuItem("사진자르기");
        Menu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

                Bounds selectionBounds = rubberBandSelection.getBounds();

                crop( selectionBounds);

            }
        });
		
		ContextMenu contextMenu = new ContextMenu();
	    contextMenu.getItems().add(Menu);
		imageLayer.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isSecondaryButtonDown()) {
                    contextMenu.show(imageLayer, event.getScreenX(), event.getScreenY());
                }
            }
        });
	}
	
	private void setManual() {
		manualarea.setText("사진 저장하기를 \n누르기전에 꼭 \n어느용도인지 \n라디오버튼에서 \n선택해주세요 \n선택값에 따라 \n저장되는 사진의 \n크기가 다릅니다. ");
		manualarea.setEditable(false);
		manualarea.setStyle("-fx-font-size : 20px; -fx-font-weight:bold;");
	}
	private void crop(Bounds bounds) {
		   FileChooser fileChooser = new FileChooser();
	        fileChooser.setTitle("이미지 저장");
	        
	        Stage primaryStage = new Stage();
	        File file = fileChooser.showSaveDialog(primaryStage);
	        if (file == null)
	            return;

	        int width = (int) bounds.getWidth();
	        int height = (int) bounds.getHeight();

	        SnapshotParameters parameters = new SnapshotParameters();
	        parameters.setFill(Color.TRANSPARENT);
	        parameters.setViewport(new Rectangle2D( bounds.getMinX(), bounds.getMinY(), width, height));

	        WritableImage writeImage = new WritableImage( width, height);
	        imgview.snapshot(parameters, writeImage);

	        //이미지 저장에 버그가 있을경우 아래방법으로 이용해보기
	        // --------------------------------
//	        try {
//	          ImageIO.write(SwingFXUtils.fromFXImage( wi, null), "jpg", file);
//	      } catch (IOException e) {
//	          e.printStackTrace();
//	      }


	        // 버퍼이미지로 이미지 저장하기
	        BufferedImage bufImageARGB = SwingFXUtils.fromFXImage(writeImage, null);
	        BufferedImage bufImageRGB = new BufferedImage(bufImageARGB.getWidth(), bufImageARGB.getHeight(), BufferedImage.OPAQUE);

	        Graphics2D graphics = bufImageRGB.createGraphics();
	        graphics.drawImage(bufImageARGB, 0, 0, null);

	        try {

	            ImageIO.write(bufImageRGB, "png", file); 

	            System.out.println( "이미지 저장 절대경로 : " + file.getAbsolutePath());

	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        graphics.dispose();
	}
	
	@FXML
	private void handleDragOver(DragEvent event) {
		if(event.getDragboard().hasFiles()) {
			event.acceptTransferModes(TransferMode.ANY);
		}
	}
	
	@FXML
	private void handleDrop(DragEvent event) throws FileNotFoundException {
		files = event.getDragboard().getFiles();
		img = new Image(new FileInputStream(files.get(0)));
		imgview.setImage(img);
		System.out.println(files.get(0).getAbsolutePath());
	}
	
	
	@FXML
	public void resizeImage(ActionEvent event) {
		File file = new File("");
		String path = file.getAbsolutePath();
		if(imgtype.getSelectedToggle().getUserData().equals("1")) {
			File pngOriginal = new File(files.get(0).getAbsolutePath());
			int index = files.get(0).getAbsolutePath().indexOf(".");
			String format = files.get(0).getAbsolutePath().substring(index+1);
			File pngResized = new File(path + "\\src\\kr\\or\\ddit\\img\\"+LoginController.ssessionCMem.getCor_name()+"."+format);
			resize(pngOriginal,pngResized,144,124,format);
			String profile = pngResized.getAbsolutePath();
			LoginController.ssessionCMem.setMem_image(profile);
			LoginController.ssessionJMem.setMem_image(profile);
			int result = conn.getICorMemberService().updateProfileImg(LoginController.ssessionCMem);
		}else {
			File pngOriginal = new File(files.get(0).getAbsolutePath());
			int index = files.get(0).getAbsolutePath().indexOf(".");
			String format = files.get(0).getAbsolutePath().substring(index+1);
			File pngResized = new File(path + "\\src\\kr\\or\\ddit\\img\\"+LoginController.ssessionCMem.getCor_name()+"."+format);
			resize(pngOriginal,pngResized,71,196,format);
			String profile = pngResized.getAbsolutePath();
			LoginController.ssessionCMem.setMem_image(profile);
			int result = conn.getICorMemberService().updateProfileImg(LoginController.ssessionCMem);
		}
		
		Stage primaryStage = (Stage) ((Node) manualarea).getScene().getWindow();
		primaryStage.close();
	}
	
	private static void resize(File originalImage, File resizedImage,int height,int width, String format) {
		try {
			BufferedImage original = ImageIO.read(originalImage);
			BufferedImage resized = new BufferedImage(width, height, original.getType());
			Graphics2D g2 = resized.createGraphics();
			
			g2.drawImage(original, 0, 0, width, height, null);
			g2.dispose();
			ImageIO.write(resized, format, resizedImage);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static class RubberBandSelection {

        final DragContext dragContext = new DragContext();
        Rectangle rect = new Rectangle();

        Group group;


        public Bounds getBounds() {
            return rect.getBoundsInParent();
        }

        public RubberBandSelection( Group group) {

            this.group = group;

            rect = new Rectangle( 0,0,0,0);
            rect.setStroke(Color.RED);
            rect.setStrokeWidth(3);
            rect.setStyle("-fx-stroke-dash-array: 4 4 4 4;"); 
            rect.setStrokeLineCap(StrokeLineCap.ROUND);
            rect.setFill(Color.LIGHTBLUE.deriveColor(0, 1.2, 1, 0.6));

            group.addEventHandler(MouseEvent.MOUSE_PRESSED, onMousePressedEventHandler);
            group.addEventHandler(MouseEvent.MOUSE_DRAGGED, onMouseDraggedEventHandler);
            group.addEventHandler(MouseEvent.MOUSE_RELEASED, onMouseReleasedEventHandler);

        }

        EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                if( event.isSecondaryButtonDown())
                    return;

                // remove old rect
                rect.setX(0);
                rect.setY(0);
                rect.setWidth(0);
                rect.setHeight(0);
                

                group.getChildren().remove( rect);


                // prepare new drag operation
                dragContext.mouseAnchorX = event.getX();
                dragContext.mouseAnchorY = event.getY();

                rect.setX(dragContext.mouseAnchorX);
                rect.setY(dragContext.mouseAnchorY);
                rect.setWidth(0);
                rect.setHeight(0);

                group.getChildren().add( rect);

            }
        };

        EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                if( event.isSecondaryButtonDown())
                    return;

                double offsetX = event.getX() - dragContext.mouseAnchorX;
                double offsetY = event.getY() - dragContext.mouseAnchorY;

                if( offsetX > 0)
                    rect.setWidth( offsetX);
                else {
                    rect.setX(event.getX());
                    rect.setWidth(dragContext.mouseAnchorX - rect.getX());
                }

                if( offsetY > 0) {
                    rect.setHeight( offsetY);
                } else {
                    rect.setY(event.getY());
                    rect.setHeight(dragContext.mouseAnchorY - rect.getY());
                }
            }
        };


        EventHandler<MouseEvent> onMouseReleasedEventHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                if( event.isSecondaryButtonDown())
                    return;

                // remove rectangle
                // note: we want to keep the ruuberband selection for the cropping => code is just commented out
                /*
                rect.setX(0);
                rect.setY(0);
                rect.setWidth(0);
                rect.setHeight(0);

                group.getChildren().remove( rect);
                */

            }
        };
        private static final class DragContext {

            public double mouseAnchorX;
            public double mouseAnchorY;


        }
    }
	
	
}