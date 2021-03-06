package view;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import utility.StringDoubleConverter;
import utility.StringIntegerConverter;
import viewmodel.MainViewModel;


public class PrimaryController {

    @FXML
    private Circle t0Circle;

    @FXML
    private Circle t1Circle;

    @FXML
    private Circle t2Circle;

    @FXML
    private Label valueT0;

    @FXML
    private Label valueT1;

    @FXML
    private Label valueT2;

    @FXML
    private Button increaseButton;

    @FXML
    private Button decreaseButton;

    @FXML
    private Label powerPosition;

    @FXML
    private Label errorLabel = null;

    private Region root;
    private ViewHandler viewHandler;
    private MainViewModel viewModel;

    public PrimaryController() {
    }

    public void init(ViewHandler viewHandler, MainViewModel viewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;

        Bindings.bindBidirectional(valueT0.textProperty(), viewModel.t0Property(), new StringDoubleConverter(10));
        Bindings.bindBidirectional(valueT1.textProperty(), viewModel.t1Property(), new StringDoubleConverter(10));
        Bindings.bindBidirectional(valueT2.textProperty(), viewModel.t2Property(), new StringDoubleConverter(10));
        Bindings.bindBidirectional(powerPosition.textProperty(), viewModel.heaterStateProperty(), new StringIntegerConverter(0));
        errorLabel.textProperty().bindBidirectional(viewModel.errorProperty());

        increaseButton.setOnAction((evt) -> viewModel.increaseTemperature());
        decreaseButton.setOnAction((evt) -> viewModel.decreaseTemperature());

        viewModel.t0Property().addListener((evt, oldVal, newVal) -> {
            t0Circle.setStroke(viewModel.gett0Color());
            t0Circle.setOpacity(viewModel.t0Property().get() / 20);
        });

        viewModel.t1Property().addListener((evt, oldVal, newVal) -> {
            t1Circle.setStroke(viewModel.gett1Color());
            t1Circle.setOpacity(viewModel.t1Property().get() / 20);
        });

        viewModel.t2Property().addListener((evt, oldVal, newVal) -> {
            t2Circle.setStroke(viewModel.gett2Color());
            t2Circle.setOpacity(viewModel.t2Property().get() / 20);
        });
    }

    public void reset() {
//        valueT0.setText("0");
//        valueT1.setText("0");
//        valueT2.setText("0");
//        powerPosition.setText("0");
//        errorLabel.setText(null);
    }

    public Region getRoot() {
        return this.root;
    }

    @FXML
    private void showHistoryButtonPressed(){
        viewHandler.openView("secondary");
    }

}
