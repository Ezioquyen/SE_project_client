package com.example.project_client.view.controller.Quyen;

import atlantafx.base.controls.Card;
import atlantafx.base.controls.Tile;
import atlantafx.base.theme.Styles;
import com.example.project_client.model.OrderBill;
import com.example.project_client.router.Pages;
import com.example.project_client.router.Router;
import com.example.project_client.view.controller.Quyen.components.PromotionChild;
import com.example.project_client.view.controller.Quyen.event.ViewToggle;
import com.example.project_client.viewModel.Quyen.MainViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.io.IOException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainView {
    @FXML
    Pagination pagination;

    @FXML
    ListView<PromotionChild> listView;
    @FXML
    TextField filter;
    private final MainViewModel mainViewModel = new MainViewModel();

    @FXML
    void initialize() throws IOException {
        mainViewModel.initModel();
        mainViewModel.getPromotions().forEach(e -> listView.getItems().add(new PromotionChild(e)));
        filter.textProperty().addListener((observableValue, s, t1) -> filter(mainViewModel.getOrderBills(), t1));
        filter(mainViewModel.getOrderBills(), "");

    }

    @FXML
    public void switchToCreateOrderView() throws IOException {
        Router.switchTo(Pages.CREATE_ORDER_VIEW);
    }

    @FXML
    public void switchToIngredientView() throws IOException {
        Router.switchTo(Pages.INGREDIENT_VIEW);
    }

    @FXML
    public void switchToProductView() throws IOException {
        Router.switchTo(Pages.PRODUCT_VIEW);
    }

    @FXML
    void create() throws IOException {
        Router.switchTo(Pages.PROMOTION_VIEW);
    }

    private void filter(List<OrderBill> orderBillsList, String value) {
        List<OrderBill> orderBills = orderBillsList.stream().filter(e -> e.getId().contains(value)).collect(Collectors.toList());
        int size = orderBills.size();
        pagination.setPageCount(size % 6 == 0 ? size / 6 : (size / 6 + 1));
        pagination.setCurrentPageIndex(0);
        pagination.setMaxPageIndicatorCount(5);
        pagination.setPageFactory(index -> {
            VBox vBox = new VBox();
            IntStream.range(index * 6, index * 6 + 5).forEach(e -> {
                        if (e < size) vBox.getChildren().add(new MyCard(orderBills.get(e)));
                    }
            );
            return vBox;
        });
    }


    @FXML
    public void switchToStaffView() throws IOException {
        Router.switchTo(Pages.STAFF_VIEW);
    }
}

@Getter
class MyCard extends Card {
    private final OrderBill orderBill;

    public MyCard(OrderBill orderBill) {
        this.orderBill = orderBill;
        getStyleClass().add(Styles.ELEVATED_2);
        setMinWidth(250);
        setHeader(new Tile(
                orderBill.getId(),
                orderBill.getUserStaffId()
        ));
        setBody(new Label(orderBill.getBuyDate()));
        setOnMouseClicked(e -> {
            try {
                gotoOrderBillView(orderBill);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void gotoOrderBillView(OrderBill orderBill) throws IOException {
        ViewToggle.setIsCreateBill(false);
        ViewToggle.setOrderBill(orderBill);
        Router.switchTo(Pages.ORDER_BILL_VIEW);
    }
}