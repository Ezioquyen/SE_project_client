package com.example.project_client.view.controller.Quyen.components;

    import com.example.project_client.model.Product;
    import javafx.geometry.Pos;
    import javafx.scene.control.Button;
    import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
    import javafx.scene.control.TextField;
    import javafx.scene.layout.HBox;
    import javafx.scene.layout.Priority;
    import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
    import lombok.Getter;

    import java.text.NumberFormat;
    import java.util.Locale;

@Getter
public class ProductCount extends VBox {
    private final Product product;
    private final Label textField = new Label("1");
    private final Button sub = new Button("-");
    private final Button add = new Button("+");
    private  Label label2;

        public ProductCount(Product product) {
            this.product = product;
            initializeUI();
        }

        private void initializeUI() {
            HBox hBox = new HBox();
            Label label1 = new Label(product.getName());
            Region region = new Region();
            HBox.setHgrow(region, Priority.ALWAYS);
             label2 =  new Label(NumberFormat.getNumberInstance(Locale.US).format(product.getPrice()) + " VND");
            hBox.getChildren().addAll(label1);
            VBox vBox = new VBox(textField);
            vBox.setPrefSize(40,25);
            vBox.setAlignment(Pos.CENTER);
            getChildren().addAll(hBox, new HBox(sub,vBox ,add,region,label2));
        }
}
