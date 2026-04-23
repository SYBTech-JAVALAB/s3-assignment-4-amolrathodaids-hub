import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BasicCalculator extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Integer Arithmetic Calculator");

        // Create UI Controls
        Label lblNum1 = new Label("Number 1:");
        Label lblNum2 = new Label("Number 2:");
        Label lblResult = new Label("Result:");

        TextField txtNum1 = new TextField();
        TextField txtNum2 = new TextField();
        TextField txtResult = new TextField();
        txtResult.setEditable(false); // Result field should be read-only

        Button btnAdd = new Button("+");
        Button btnSub = new Button("-");
        Button btnMul = new Button("*");
        Button btnDiv = new Button("/");

        // Layout - GridPane for labels and fields
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(lblNum1, 0, 0);
        grid.add(txtNum1, 1, 0);
        grid.add(lblNum2, 0, 1);
        grid.add(txtNum2, 1, 1);
        grid.add(lblResult, 0, 2);
        grid.add(txtResult, 1, 2);

        // HBox for buttons to align them horizontally
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().addAll(btnAdd, btnSub, btnMul, btnDiv);
        grid.add(hbBtn, 0, 3, 2, 1);

        // Event Handling
        btnAdd.setOnAction(e -> calculate(txtNum1, txtNum2, txtResult, '+'));
        btnSub.setOnAction(e -> calculate(txtNum1, txtNum2, txtResult, '-'));
        btnMul.setOnAction(e -> calculate(txtNum1, txtNum2, txtResult, '*'));
        btnDiv.setOnAction(e -> calculate(txtNum1, txtNum2, txtResult, '/'));

        Scene scene = new Scene(grid, 350, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculate(TextField n1, TextField n2, TextField res, char op) {
        try {
            int num1 = Integer.parseInt(n1.getText());
            int num2 = Integer.parseInt(n2.getText());
            int result = 0;

            switch (op) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/': 
                    if (num2 == 0) {
                        res.setText("Err: Div/0");
                        return;
                    }
                    result = num1 / num2; 
                    break;
            }
            res.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            res.setText("Invalid Input");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}