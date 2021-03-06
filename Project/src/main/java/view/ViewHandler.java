package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

public class ViewHandler {
    private ViewModelFactory viewModelFactory;
    private PrimaryController primaryController;
    private SecondaryController secondaryController;
    private Scene currentScene;
    private Stage primaryStage;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        currentScene = new Scene(new Region());
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        openView("temperature");
    }

    public void openView(String id) {
        Region root = null;
        switch (id) {
            case "temperature":
                root = loadHeatView("MainView.fxml");
                break;
            case "secondary":
                root = loadSecondaryView("secondary.fxml");
        break;
        }
        currentScene.setRoot(root);
        String title = "Temperature";
        if (root.getUserData() != null) {
            title += root.getUserData();
        }
        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
/*        primaryStage.setWidth(1050);
        primaryStage.setHeight(700);*/
        primaryStage.show();
    }

    public void closeView() {
        primaryStage.close();
    }

    private Region loadHeatView(String fxmlFile) {
        Region root = null;
        if (primaryController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxmlFile));
                root = loader.load();
                primaryController = loader.getController();
                primaryController.init(this, viewModelFactory.getHeaterViewModel(), root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            primaryController.reset();
        }
        return primaryController.getRoot();
    }

    private Region loadSecondaryView(String fxmlFile) {
        Region root = null;
        if (secondaryController == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxmlFile));
                root = loader.load();
                secondaryController = loader.getController();
                secondaryController.init(this,viewModelFactory.getSecondaryViewModel(), root);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else  {
            secondaryController.reset();
        }
        return  secondaryController.getRoot();



    }
}
