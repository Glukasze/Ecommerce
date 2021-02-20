package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/category-filter"})
public class CategoryFilter extends HttpServlet {

    Order order = new Order();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        ArrayList<Product> categorySelected = new ArrayList<>();
        ArrayList<Product> supplierSelected = new ArrayList<>();
        ArrayList<Product> productsSelected;

        filterSelect(req, productDataStore, productCategoryDataStore, supplierDataStore, categorySelected, supplierSelected);

        categorySelected.retainAll(supplierSelected);
        productsSelected = categorySelected;


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("selectedCategory", req.getParameter("category"));
        context.setVariable("selectedSupplier", req.getParameter("supplier"));
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());
        context.setVariable("category", productCategoryDataStore.find(1));
        context.setVariable("productsAll", productDataStore.getAll());
        context.setVariable("productsSelected", productsSelected);
        context.setVariable("itemsInCart", order.getProductsOrdered().size());


        engine.process("product/filtered.html", context, resp.getWriter());
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!(req.getParameter("add-to-cart") == null)) {
            Product temp = ProductDaoMem.getInstance().find(Integer.parseInt(req.getParameter("add-to-cart")));
            order.addProduct(temp);
        }
        doGet(req, resp);
    }

    private void filterSelect(HttpServletRequest req, ProductDao productDataStore, ProductCategoryDao productCategoryDataStore, SupplierDao supplierDataStore, ArrayList<Product> categorySelected, ArrayList<Product> supplierSelected) {
        switch (req.getParameter("category")) {
            case "no-filter":
                categorySelected.addAll(productDataStore.getAll());
                break;
            case "tablet":
                categorySelected.addAll(productDataStore.getBy(productCategoryDataStore.find(1)));
                break;
            case "smartphone":
                categorySelected.addAll(productDataStore.getBy(productCategoryDataStore.find(2)));
                break;
            case "laptop":
                categorySelected.addAll(productDataStore.getBy(productCategoryDataStore.find(3)));
                break;
            case "TV":
                categorySelected.addAll(productDataStore.getBy(productCategoryDataStore.find(4)));
                break;
        }
        switch (req.getParameter("supplier")) {
            case "no-filter":
                supplierSelected.addAll(productDataStore.getAll());
                break;
            case "Amazon":
                supplierSelected.addAll(productDataStore.getBy(supplierDataStore.find(1)));
                break;
            case "Lenovo":
                supplierSelected.addAll(productDataStore.getBy(supplierDataStore.find(2)));
                break;
            case "Apple":
                supplierSelected.addAll(productDataStore.getBy(supplierDataStore.find(3)));
                break;
            case "LG":
                supplierSelected.addAll(productDataStore.getBy(supplierDataStore.find(4)));
                break;
            case "Galaxy":
                supplierSelected.addAll(productDataStore.getBy(supplierDataStore.find(5)));
                break;
            case "Asus":
                supplierSelected.addAll(productDataStore.getBy(supplierDataStore.find(6)));
                break;
        }
    }

}
