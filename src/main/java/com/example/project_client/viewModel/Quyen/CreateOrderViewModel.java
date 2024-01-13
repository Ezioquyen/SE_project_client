package com.example.project_client.viewModel.Quyen;

import com.example.project_client.model.OrderBill;
import com.example.project_client.model.Product;
import com.example.project_client.model.Promotion;
import com.example.project_client.repository.ProductRepository;
import com.example.project_client.repository.PromotionRepository;

import javafx.beans.property.SimpleIntegerProperty;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateOrderViewModel {

    private final PromotionRepository promotionRepository = new PromotionRepository();
    @Getter
    private List<Product> products;
    @Getter
    private OrderBill orderBill = new OrderBill();
    @Getter
    private Map<Product, SimpleIntegerProperty> count = new HashMap<>();
    @Getter
    private final SimpleIntegerProperty original = new SimpleIntegerProperty(0);
    @Getter
    private final SimpleIntegerProperty total = new SimpleIntegerProperty(0);
    @Getter
    private final SimpleIntegerProperty deduction = new SimpleIntegerProperty(0);
    @Getter
    private Promotion promotion;
    @Setter
    private Boolean applyPromotion = false;

    public void initData() throws IOException {
        original.addListener(((observableValue, number, t1) -> orderBill.setOriginal(t1.intValue())));
        deduction.addListener(((observableValue, number, t1) -> orderBill.setDeduction(t1.intValue())));
        total.addListener(((observableValue, number, t1) -> orderBill.setTotal(t1.intValue())));
        products = ProductRepository.getProductsApi();
        promotion = Promotion.fromData(promotionRepository.getPromotionByDate(LocalDate.now()));
    }

    public void addMoreProduct(Product product) {
        count.get(product).set(count.get(product).getValue() + 1);
        original.setValue(original.getValue() + product.getPrice());
        if (promotion != null&&applyPromotion)
            if (promotion.getProducts().get(product.getId()) != null) {
                deduction.setValue(deduction.getValue() + product.getPrice() * promotion.getProducts().get(product.getId()) / 100);
            }
        total.setValue(original.getValue() - deduction.getValue());
    }

    public void reduceProduct(Product product) {
        count.get(product).set(count.get(product).getValue() - 1);
        original.setValue(original.getValue() - product.getPrice());
        if (count.get(product).getValue() == 0) {
            count.remove(product);
        }
        if (promotion != null&&applyPromotion)
            if (promotion.getProducts().get(product.getId()) != null) {
                deduction.setValue(deduction.getValue() - product.getPrice() * promotion.getProducts().get(product.getId()) / 100);
            }
        total.setValue(original.getValue() - deduction.getValue());
    }

    public void initCount(Product product) {
        count.put(product, new SimpleIntegerProperty(0));
        addMoreProduct(product);
    }

    public boolean check(Product product) {
        return count.get(product) == null;
    }
    public void setProductOfOrderBill(){
        orderBill.getProducts().clear();
        count.forEach((product, simpleIntegerProperty) -> {
            Map<String,Object> productData = new HashMap<>();
            productData.put("productId",product.getId());
            productData.put("count",simpleIntegerProperty.intValue());
            productData.put("image",product.getImage());
            productData.put("price",product.getPrice());
            productData.put("name",product.getName());
            orderBill.getProducts().add(productData);
        });
    }
    public void resetPromotion(){
        orderBill.setPromotion("");
        deduction.setValue(0);
        total.set(original.getValue());
    }
    public void updatePromotion(){
        orderBill.setPromotion("");
        deduction.setValue(0);
        count.forEach(((product, simpleIntegerProperty) -> {
            if(promotion.getProducts().get(product.getId())!=null){
                deduction.setValue(deduction.getValue()+ simpleIntegerProperty.intValue()* (promotion.getProducts().get(product.getId())*product.getPrice()/100));
            }
        }));
        total.setValue(original.getValue()-deduction.getValue());
        orderBill.setPromotion(promotion.getName());
    }
}
