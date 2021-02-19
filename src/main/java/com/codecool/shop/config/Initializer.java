package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier apple = new Supplier("Apple", "Computers");
        supplierDataStore.add(apple);
        Supplier lg = new Supplier("LG", "TV");
        supplierDataStore.add(lg);
        Supplier galaxy = new Supplier("Galaxy", "digital content and services");
        supplierDataStore.add(galaxy);
        Supplier asus = new Supplier("Asus", "Computers");
        supplierDataStore.add(asus);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory smartphone = new ProductCategory("smartphone", "Hardware", "A mobile device that combines cellular and mobile computing functions into one unit.");
        productCategoryDataStore.add(smartphone);
        ProductCategory laptop = new ProductCategory("laptop", "Hardware", "A small, portable personal computer (PC) typically having a thin LCD or LED computer screen");
        productCategoryDataStore.add(laptop);
        ProductCategory tv = new ProductCategory("TV", "Hardware", "A device with a screen for receiving television signals.");
        productCategoryDataStore.add(tv);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));

        productDataStore.add(new Product("iPhone 7", 199, "USD", "Advanced dual camera system which offers up to 2 hours longer battery life. High-definition touch screen display.", smartphone, apple));
        productDataStore.add(new Product("iPhone 12", 699, "USD", "5G speed. A14 Bionic, the fastest chip in a smartphone. An edge-to-edge OLED display.", smartphone, apple));
        productDataStore.add(new Product("Galaxy S21 Ultra 5G", 499, "USD", "The highest resolution photos and video on a smartphone.Galaxy's fastest processor yet. A battery that goes all-day—and then some.", smartphone, galaxy));

        productDataStore.add(new Product("MacBook Air M1", 999, "USD", "Now with the new Apple M1 chip for faster-than-ever performance and all-new capabilities", laptop, apple));
        productDataStore.add(new Product("VivoBook X509JA", 799, "USD", "Delivers powerful performance and immersive visuals. wide viewing angles and a matte anti-glare coating", laptop, asus));
        productDataStore.add(new Product("ExpertBook", 999, "USD", "Mobility, lightness, next-gen power and military-grade toughness.", laptop, asus));

        productDataStore.add(new Product("LG LED 55NANO867NA", 899, "USD", "The most advanced UHD technology, Sublime 4K Ultra HD picture quality with pure and realistic colours", tv, lg));
        productDataStore.add(new Product("LG OLED OLED77CX3LA", 3299, "USD", "Self-lighting OLED: Perfect Black, Intense Color, Infinite Contrast, Dolby Vision IQ and Dolby Atmos", tv, lg));
        productDataStore.add(new Product("LG OLED 65BX3LB", 2099, "USD", "WebOS + ThinQ AI w/ Magic Remote. Gaming: G-SYNC Compatible, FreeSync, VRR, ALLM", tv, lg));
    }
}
