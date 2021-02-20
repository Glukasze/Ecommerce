package com.codecool.shop.controller;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import com.codecool.shop.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/", "/#"})
public class ProductController extends HttpServlet {

    Order order = new Order();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = getWebContext(req, resp, productDataStore, productCategoryDataStore, supplierDataStore);

        engine.process("product/index.html", context, resp.getWriter());
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean inputFieldNotEmpty = !(req.getParameter("add-to-cart") == null);
        if (inputFieldNotEmpty) {
            Product temp = ProductDaoMem.getInstance().find(Integer.parseInt(req.getParameter("add-to-cart")));
            order.addProduct(temp);
        }
        doGet(req, resp);
    }

    private WebContext getWebContext(HttpServletRequest req, HttpServletResponse resp, ProductDao productDataStore, ProductCategoryDao productCategoryDataStore, SupplierDao supplierDataStore) {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());
        context.setVariable("category", productCategoryDataStore.find(1));
        context.setVariable("productsAll", productDataStore.getAll());
        context.setVariable("itemsInCart", order.getProductsOrdered().size());
        return context;
    }

}
