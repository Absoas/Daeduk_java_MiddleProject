package kr.or.ddit.controller.cor.applyinfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import com.jfoenix.controls.JFXButton;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kr.or.ddit.controller.cor.corApply.CorApplyController;
import kr.or.ddit.rmi.client.ClientConnectFactory;
import kr.or.ddit.rmi.interf.IRemote;
import kr.or.ddit.test.TestVO;

public class CodingResultController implements Initializable {

	@FXML
	AnchorPane pane;
	@FXML
	TextArea resultText,testcont;
	@FXML
	JFXButton compile, submit;
	
	IRemote conn;

	public static String id = CorApplyController.selJobMem.getMem_id();

	// 생명주기 종료 될 때 executor.shutdown();
	private CodeArea codeArea;
	private ExecutorService executor;
	private TestVO test;

	private static final String[] KEYWORDS = new String[] { "abstract", "assert", "boolean", "break", "byte", "case",
			"catch", "char", "class", "const", "continue", "default", "do", "double", "else", "enum", "extends",
			"final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface",
			"long", "native", "new", "package", "private", "protected", "public", "return", "short", "static",
			"strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void",
			"volatile", "while" };
	private static final String[] SYSTEMS = new String[] { "out", "err" };
	private static final String[] LOCALS = new String[] { "args", "i", "sum" };

	private static final String LOCAL_PATTERN = "\\b(" + String.join("|", LOCALS) + ")\\b";
	private static final String SYSTEMS_PATTERN = "\\b(" + String.join("|", SYSTEMS) + ")\\b";
	private static final String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
	private static final String PAREN_PATTERN = "\\(|\\)";
	private static final String BRACE_PATTERN = "\\{|\\}";
	private static final String BRACKET_PATTERN = "\\[|\\]";
	private static final String SEMICOLON_PATTERN = "\\;";
	private static final String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
	private static final String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";
	private static final Pattern PATTERN = Pattern.compile("(?<KEYWORD>" + KEYWORD_PATTERN + ")" + "|(?<PAREN>"
			+ PAREN_PATTERN + ")" + "|(?<BRACE>" + BRACE_PATTERN + ")" + "|(?<BRACKET>" + BRACKET_PATTERN + ")"
			+ "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")" + "|(?<STRING>" + STRING_PATTERN + ")" + "|(?<COMMENT>"
			+ COMMENT_PATTERN + ")" + "|(?<SYSTEMS>" + SYSTEMS_PATTERN + ")" + "|(?<LOCAL>" + LOCAL_PATTERN + ")"

	);

	private static final String sampleCode = String.join("\n", new String[] {});

	public static String setPath() {
		File path = new File("");
		String save = path.getAbsolutePath();
		return save;
	}

	private void setCoding() {
		codeArea.replaceText(0, 0, "");
		String source = "";
		try {
			File file = new File(setPath() + "/sourceFolder/" + id + ".java");
			FileReader filereader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(filereader);
			String line = "";
			while ((line = bufReader.readLine()) != null) {
				source += line + "\n";
			}
			bufReader.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			e.printStackTrace();
		}
		codeArea.replaceText(source);
	}

	private void compileCoding() {
		try {
			File file = new File(setPath() + "/sourceFolder/"+id+".java");
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter(file));
				bw.write(codeArea.getText());
				bw.newLine();
				bw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (bw != null)
					try {
						bw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}

			AccessCmd cmd = new AccessCmd();
			cmd.execCommand(id);
			resultText.setText("소스제출을 성공했습니다.");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void submitCoding() {
		AccessCmd cmd = new AccessCmd();
		resultText.setStyle("-fx-font-size:26px;");
		resultText.setText(cmd.compileCommand(id));
	}

	private void resetText() {
		setCoding();
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conn = ClientConnectFactory.getClientConnect();
		test = CorApplyController.testInfo;
		testcont.setText(test.getTest_content());
		compile.toFront();
		submit.toFront();
		compile.setOnAction(event -> compileCoding());
		submit.setOnAction(event -> submitCoding());
		resultText.setEditable(false);
		testcont.setEditable(false);
		
		
		executor = Executors.newSingleThreadExecutor();
		codeArea = new CodeArea();
		codeArea.setEditable(false);
		codeArea.setPrefSize(1000, 400);
		codeArea.setLayoutX(30);
		codeArea.setLayoutY(50);
		codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
		// call when no longer need it: `cleanupWhenFinished.unsubscribe();`
		codeArea.richChanges().filter(ch -> !ch.getInserted().equals(ch.getRemoved())).subscribe(change -> {
			codeArea.setStyleSpans(0, computeHighlighting(codeArea.getText().toLowerCase()));
		});

		setCoding();
		pane.getChildren().add((new VirtualizedScrollPane<>(codeArea)));
		pane.getStylesheets().add(CodingResultController.class.getResource("java-keywords.css").toExternalForm());
		codeArea.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.F1) {
				String aa = codeArea.getText();
				System.out.println(aa);
				if (aa.contains("main ")) {
					aa = aa.replaceAll("main ", "public static void main(String[] args) {\n\n  }");
				} else if (aa.contains("syso")) {
					aa = aa.replaceAll("syso", "System.out.println();");
				} else if (aa.contains("for.")) {
					aa = aa.replaceAll("for.", "for (int i = 0; i < args.length; i++) {\n\n     }");
				}
				codeArea.replaceText(aa);
			}
		});

	}
	
	private Task<StyleSpans<Collection<String>>> computeHighlightingAsync() {
		String text = codeArea.getText();
		Task<StyleSpans<Collection<String>>> task = new Task<StyleSpans<Collection<String>>>() {
			@Override
			protected StyleSpans<Collection<String>> call() throws Exception {
				return computeHighlighting(text);
			}
		};
		executor.execute(task);
		return task;
	}

	private void applyHighlighting(StyleSpans<Collection<String>> highlighting) {
		codeArea.setStyleSpans(0, highlighting);
	}

	private static StyleSpans<Collection<String>> computeHighlighting(String text) {
		Matcher matcher = PATTERN.matcher(text);
		int lastKwEnd = 0;
		StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
		while (matcher.find()) {
			String styleClass = matcher.group("LOCAL") != null ? "local"
					: matcher.group("SYSTEMS") != null ? "system"
							: matcher.group("KEYWORD") != null ? "keyword"
									: matcher.group("PAREN") != null ? "paren"
											: matcher.group("BRACE") != null ? "brace"
													: matcher.group("BRACKET") != null ? "bracket"
															: matcher.group("SEMICOLON") != null ? "semicolon"
																	: matcher.group("STRING") != null ? "string"
																			: matcher.group("COMMENT") != null
																					? "comment"
																					: null;
			/* never happens */ assert styleClass != null;
			spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
			spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
			lastKwEnd = matcher.end();
		}
		spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
		return spansBuilder.create();
	}
	
	@FXML
	private void close(ActionEvent event) {
		Stage resultStage = (Stage) ((Node)testcont).getScene().getWindow();
		resultStage.close();
	}
}