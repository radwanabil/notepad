package notepadfx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.IndexRange;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class notepadUI extends BorderPane {

    protected final MenuBar menuBar;
    protected final Menu menu;
    protected final MenuItem menuItem;
    protected final MenuItem menuItem0;
    protected final MenuItem menuItem1;
    protected final MenuItem menuItem2;
    protected final Menu menu0;
    protected final MenuItem menuItem3;
    protected final MenuItem menuItem4;
    protected final MenuItem menuItem5;
    protected final MenuItem menuItem6;
    protected final MenuItem menuItem7;
    protected final MenuItem menuItem8;
    protected final Menu menu1;
    protected final MenuItem menuItem9;
    protected final TextArea textArea;
    protected final Stage stage;
    String currentPath;

    public void saveAs(String txt, File file) {

        try {
            FileWriter fw = new FileWriter(file);
            System.out.println("After create writer");
            fw.write(textArea.getText());
            fw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public notepadUI(Stage stage) {
        this.stage = stage;
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        menuBar = new MenuBar();
        menu = new Menu();
        menuItem = new MenuItem();
        menuItem0 = new MenuItem();
        menuItem1 = new MenuItem();
        menuItem2 = new MenuItem();
        menu0 = new Menu();
        menuItem3 = new MenuItem();
        menuItem4 = new MenuItem();
        menuItem5 = new MenuItem();
        menuItem6 = new MenuItem();
        menuItem7 = new MenuItem();
        menuItem8 = new MenuItem();
        menu1 = new Menu();
        menuItem9 = new MenuItem();
        textArea = new TextArea();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(menuBar, javafx.geometry.Pos.CENTER);

        menu.setMnemonicParsing(false);
        menu.setText("File");

        menuItem.setMnemonicParsing(false);
        menuItem.setText("New");
        menuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        menuItem.setOnAction((ActionEvent ae)
                -> {

            String txt = textArea.getText();
            if (txt.length() > 0) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Do you want to save changes?");
                
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {

                    
                    FileChooser fc = new FileChooser();
                    FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter("undefined", ".txt");
                    File savefile = fc.showSaveDialog(stage);
                    
                    if (savefile != null) {
                        saveAs(txt, savefile);
                    }
                    textArea.clear();
                }
            } else {
                textArea.clear();
            }
            
        });

        menuItem0.setMnemonicParsing(false);
        menuItem0.setText("Open");
        menuItem0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                String txt = textArea.getText();
                if (txt.length() > 0) {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Do you want to save changes?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {

                        txt = textArea.getText();
                        FileChooser fc = new FileChooser();
                        FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter("undefined", ".txt");
                        File savefile = fc.showSaveDialog(stage);
                        System.out.println("After Choosing file name");
                        if (savefile != null) {
                            saveAs(txt, savefile);
                        }
                    }
                } else {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Select File");
                    File selectedFile = fileChooser.showOpenDialog(stage);
                    if (selectedFile != null) {
                        String path = selectedFile.getPath();
                        currentPath = path;
                        try {
                            FileInputStream fileInputStream = new FileInputStream(path);
                            byte[] text = new byte[fileInputStream.available()];
                            fileInputStream.read(text);
                            textArea.setText(new String(text));
                            String currentText = textArea.getText();
                            String noteName = selectedFile.getName();
                            stage.setTitle(noteName);
                            fileInputStream.close();
                        } catch (FileNotFoundException ex) {
                            System.out.println(ex.getMessage());
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }

            }
        });

        menuItem1.setMnemonicParsing(false);
        menuItem1.setText("Save");
        menuItem1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                String txt = textArea.getText();
                FileChooser fc = new FileChooser();
                FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter("undefined", ".txt");
                File savefile = fc.showSaveDialog(stage);
                System.out.println("After Choosing file name");
                if (savefile != null) {
                    saveAs(txt, savefile);
                }
            }

        });

        menuItem2.setMnemonicParsing(false);
        menuItem2.setText("Exit");
        menuItem2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                if (textArea.getText().length() > 0) {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Do you want to save changes?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {

                        String txt = textArea.getText();
                        FileChooser fc = new FileChooser();
                        FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter("undefined", ".txt");
                        File savefile = fc.showSaveDialog(stage);
                        System.out.println("After Choosing file name");
                        if (savefile != null) {
                            saveAs(txt, savefile);
                        }
                    }
                } else {

                    System.exit(0);
                }

            }
        });
        menu0.setMnemonicParsing(false);
        menu0.setText("Edit");

        menuItem3.setMnemonicParsing(false);
        menuItem3.setText("Undo");

        menuItem3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (textArea.isUndoable()) {
                    textArea.undo();
                }

            }
        });

        menuItem4.setMnemonicParsing(false);
        menuItem4.setText("Cut");
        menuItem4.setAccelerator(KeyCombination.keyCombination("Ctrl+x"));
        menuItem4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String txt = textArea.getSelectedText();
                content.putString(txt);
                clipboard.setContent(content);
                textArea.replaceSelection(" ");

            }
        });

        menuItem5.setMnemonicParsing(false);
        menuItem5.setText("Copy");
        menuItem5.setAccelerator(KeyCombination.keyCombination("Ctrl+c"));
        menuItem5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String txt = textArea.getSelectedText();
                content.putString(txt);
                clipboard.setContent(content);

            }
        });

        menuItem6.setMnemonicParsing(false);
        menuItem6.setText("Paste");
        menuItem6.setAccelerator(KeyCombination.keyCombination("Ctrl+v"));
        menuItem6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String txt = clipboard.getString();
                textArea.insertText(textArea.getCaretPosition(), txt);

            }
        });

        menuItem7.setMnemonicParsing(false);
        menuItem7.setText("Delete");
        menuItem7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                IndexRange r = textArea.getSelection();
                textArea.deleteText(r);

            }
        });

        menuItem8.setMnemonicParsing(false);
        menuItem8.setText("Select all");
        menuItem8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.selectAll();        // select all
            }
        });

        menu1.setMnemonicParsing(false);
        menu1.setText("Help");

        menuItem9.setMnemonicParsing(false);
        menuItem9.setText("About notepad");
        setTop(menuBar);
        EventHandler<ActionEvent> ActionEvent;
        menuItem9.setOnAction((ActionEvent ae) -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("About");
            alert.setHeaderText("Our notepad");
            alert.setContentText("Our notepad is made with javafx");
            alert.showAndWait();

        });

        BorderPane.setAlignment(textArea, javafx.geometry.Pos.CENTER);
        textArea.setPrefHeight(200.0);
        textArea.setPrefWidth(200.0);
        setCenter(textArea);

        menu.getItems().add(menuItem);
        menu.getItems().add(menuItem0);
        menu.getItems().add(menuItem1);
        menu.getItems().add(menuItem2);
        menuBar.getMenus().add(menu);
        menu0.getItems().add(menuItem3);
        menu0.getItems().add(menuItem4);
        menu0.getItems().add(menuItem5);
        menu0.getItems().add(menuItem6);
        menu0.getItems().add(menuItem7);
        menu0.getItems().add(menuItem8);
        menuBar.getMenus().add(menu0);
        menu1.getItems().add(menuItem9);
        menuBar.getMenus().add(menu1);

    }
}
