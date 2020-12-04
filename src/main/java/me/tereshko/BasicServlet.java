package me.tereshko;

import javax.servlet.*;
import java.io.IOException;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicServlet implements Servlet {

    private static Logger logger = LoggerFactory.getLogger(BasicServlet.class);
    private transient ServletConfig config;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("New request");

        Product[] product = new Product[10];

        for (int i = 0; i <= 9; i++) {
            product[i] = new Product();
            product[i].setId(generateId());
            product[i].setTitle(generateTitle());
            product[i].setCost(generateCost());
        }


        servletResponse.getWriter().println("<h1>products:</h1>");
        for (int i = 0; i <= 9; i++) {
            servletResponse.getWriter().println(""
                    + " <b>id:</b> " + product[i].getId()
                    + " <b>title:</b> " + product[i].getTitle()
                    + " <b>cost:</b> " + product[i].getCost()
                    + "<br />"
            );
        }

    }

    private int generateId() {
        int productID;
        Random random = new Random();
        productID = random.nextInt(999);
        return productID;
    }

    private String generateTitle() {
        String productTitle;
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZqwertyuiopasdfghjklzxcvbnm1234567890";
        StringBuilder salt = new StringBuilder();
        Random random = new Random();
        while (salt.length() < 100) {
            int index = (int) (random.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        productTitle = salt.toString();
        return productTitle;
    }

    private int generateCost() {
        int productCost;
        Random random = new Random();
        productCost = random.nextInt(55);
        return productCost;
    }


    @Override
    public String getServletInfo() {
        return "me.tereshko.BasicServlet";
    }

    @Override
    public void destroy() {
        logger.info("Servlet {} destroyed", getServletInfo());
    }
}
