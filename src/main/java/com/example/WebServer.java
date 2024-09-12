package com.example;

// Source code is decompiled from a .class file using FernFlower decompiler.
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.H1;
import com.hp.gagawa.java.elements.Head;
import com.hp.gagawa.java.elements.Html;
import com.hp.gagawa.java.elements.P;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Text;
import com.hp.gagawa.java.elements.Th;
import com.hp.gagawa.java.elements.Title;
import com.hp.gagawa.java.elements.Tr;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;


public class WebServer {
   public WebServer() {
   }

   public static void main(String[] args) throws IOException {
      HttpServer server = HttpServer.create(new InetSocketAddress(8500), 0);
      HttpContext root = server.createContext("/");
      root.setHandler(WebServer::handleRequest);
      HttpContext context = server.createContext("/users");
      context.setHandler(WebServer::handleRequestUser);
      HttpContext product = server.createContext("/products");
      product.setHandler(WebServer::handleRequestOneProduct);
      HttpContext allproducts = server.createContext("/products/all");
      allproducts.setHandler(WebServer::handleRequestAllProducts);
      HttpContext productSearch = server.createContext("/products/search");
      productSearch.setHandler(WebServer::handleRequestProductSearch);
      server.start();
   }

   private static void handleRequestProductSearch(HttpExchange exchange) throws IOException {
      String uriString = exchange.getRequestURI().toString();

      // https://chat.openai.com/share/d626a81f-2624-45c3-843a-26fc1bad68d7
      String[] queryParams = uriString.split("&");
      double minPrice = 0.0;
      double maxPrice = 0.0;

      for (String param : queryParams) {
         String[] keyValue = param.split("=");
         if (keyValue.length == 2) {
            String key = keyValue[0];
            String value = keyValue[1];

            if (key.equals("minprice")) {
               minPrice = Double.parseDouble(URLDecoder.decode(value, "UTF-8"));
            } else if (key.equals("maxprice")) {
               maxPrice = Double.parseDouble(URLDecoder.decode(value, "UTF-8"));
            }
         }
      }

      System.out.println("Min Price: " + minPrice);
      System.out.println("Max Price: " + maxPrice);


      DataAccess dao = new remoteDataAdapter();
      dao.connect();
      List<productModel> list = dao.getAllProducts();
      Html html = new Html();
      Head head = new Head();
      html.appendChild(head);
      Title title = new Title();
      title.appendText("Product list");
      head.appendChild(title);
      Body body = new Body();
      html.appendChild(body);
      H1 h1 = new H1();
      h1.appendText("Product list");
      body.appendChild(h1);
      P para = new P();
      para.appendChild(new Text("The server time is " + LocalDateTime.now()));
      body.appendChild(para);
      /* para = new P();
      para.appendChild(new Text("The server has " + list.size() + " products."));
      body.appendChild(para); */
      Table table = new Table();
      Tr row = new Tr();
      Th header = new Th();
      header.appendText("ProductID");
      row.appendChild(header);
      header = new Th();
      header.appendText("Product name");
      row.appendChild(header);
      header = new Th();
      header.appendText("Price");
      row.appendChild(header);
      header = new Th();
      header.appendText("Quantity");
      row.appendChild(header);
      table.appendChild(row);
      Iterator var13 = list.iterator();

      while (var13.hasNext()) {
         productModel product = (productModel) var13.next();
         // do checks here
         if ((product.getPrice().doubleValue() >= minPrice) && (product.getPrice().doubleValue() <= maxPrice)) {
            row = new Tr();
            Td cell = new Td();
            cell.appendText(String.valueOf(product.getProductID()));
            row.appendChild(cell);
            cell = new Td();
            cell.appendText(product.getName());
            row.appendChild(cell);
            cell = new Td();
            cell.appendText(String.valueOf(product.getPrice()));
            row.appendChild(cell);
            cell = new Td();
            cell.appendText(String.valueOf(product.getQuantity()));
            row.appendChild(cell);
            table.appendChild(row);
         }
      }

      table.setBorder("1");
      html.appendChild(table);
      String response = html.write();
      System.out.println(response);
      exchange.sendResponseHeaders(200, (long) response.getBytes().length);
      OutputStream os = exchange.getResponseBody();
      os.write(response.getBytes());
      os.close();
   }

   private static void handleRequest(HttpExchange exchange) throws IOException {
      Html html = new Html();
      Head head = new Head();
      html.appendChild(head);
      Title title = new Title();
      title.appendText("Online shopping web server");
      head.appendChild(title);
      Body body = new Body();
      H1 h1 = new H1();
      h1.appendText("Welcome to my online shopping center");
      body.appendChild(h1);
      P para = new P();
      A link = new A("/products/all", "/products/all");
      link.appendText("Product list");
      para.appendChild(link);
      body.appendChild(para);
      html.appendChild(body);
      String response = html.write();
      exchange.sendResponseHeaders(200, (long) response.getBytes().length);
      OutputStream os = exchange.getResponseBody();
      os.write(response.getBytes());
      os.close();
   }

   private static void handleRequestUser(HttpExchange exchange) throws IOException {
      String response = "Hi there! I am a simple web server!";
      exchange.sendResponseHeaders(200, (long) response.getBytes().length);
      OutputStream os = exchange.getResponseBody();
      os.write(response.getBytes());
      os.close();
   }

   private static void handleRequestAllProducts(HttpExchange exchange) throws IOException {
      // String url = "jdbc:sqlite:store.db";
      DataAccess dao = new remoteDataAdapter();
      dao.connect();
      List<productModel> list = dao.getAllProducts();
      Html html = new Html();
      Head head = new Head();
      html.appendChild(head);
      Title title = new Title();
      title.appendText("Product list");
      head.appendChild(title);
      Body body = new Body();
      html.appendChild(body);
      H1 h1 = new H1();
      h1.appendText("Product list");
      body.appendChild(h1);
      P para = new P();
      para.appendChild(new Text("The server time is " + LocalDateTime.now()));
      body.appendChild(para);
      para = new P();
      para.appendChild(new Text("The server has " + list.size() + " products."));
      body.appendChild(para);
      Table table = new Table();
      Tr row = new Tr();
      Th header = new Th();
      header.appendText("ProductID");
      row.appendChild(header);
      header = new Th();
      header.appendText("Product name");
      row.appendChild(header);
      header = new Th();
      header.appendText("Price");
      row.appendChild(header);
      header = new Th();
      header.appendText("Quantity");
      row.appendChild(header);
      table.appendChild(row);
      Iterator var13 = list.iterator();

      while (var13.hasNext()) {
         productModel product = (productModel) var13.next();
         row = new Tr();
         Td cell = new Td();
         cell.appendText(String.valueOf(product.getProductID()));
         row.appendChild(cell);
         cell = new Td();
         cell.appendText(product.getName());
         row.appendChild(cell);
         cell = new Td();
         cell.appendText(String.valueOf(product.getPrice()));
         row.appendChild(cell);
         cell = new Td();
         cell.appendText(String.valueOf(product.getQuantity()));
         row.appendChild(cell);
         table.appendChild(row);
      }

      table.setBorder("1");
      html.appendChild(table);
      String response = html.write();
      System.out.println(response);
      exchange.sendResponseHeaders(200, (long) response.getBytes().length);
      OutputStream os = exchange.getResponseBody();
      os.write(response.getBytes());
      os.close();
   }

   private static void handleRequestOneProduct(HttpExchange exchange) throws IOException {
      String uri = exchange.getRequestURI().toString();
      int id = Integer.parseInt(uri.substring(uri.lastIndexOf(47) + 1));
      System.out.println(id);
      /*
       * DataAccess dao = new SQLiteDataAdapter();
       * dao.connect("jdbc:sqlite:store.db");
       * Html html = new Html();
       * Head head = new Head();
       * html.appendChild(head);
       * Title title = new Title();
       * title.appendChild(new Text("Example Page Title"));
       * head.appendChild(title);
       * Body body = new Body();
       * html.appendChild(body);
       * H1 h1 = new H1();
       * h1.appendChild(new Text("Example Page Header"));
       * body.appendChild(h1);
       * P para = new P();
       * para.appendChild(new Text("The server time is " + LocalDateTime.now()));
       * body.appendChild(para);
       * ProductModel product = dao.loadProduct(id);
       * if (product != null) {
       * para = new P();
       * para.appendText("ProductID:" + product.productID);
       * html.appendChild(para);
       * para = new P();
       * para.appendText("Product name:" + product.name);
       * html.appendChild(para);
       * para = new P();
       * para.appendText("Price:" + product.price);
       * html.appendChild(para);
       * para = new P();
       * para.appendText("Quantity:" + product.quantity);
       * html.appendChild(para);
       * } else {
       * para = new P();
       * para.appendText("Product not found");
       * html.appendChild(para);
       * }
       * 
       * String response = html.write();
       * exchange.sendResponseHeaders(200, (long)response.getBytes().length);
       * OutputStream os = exchange.getResponseBody();
       * os.write(response.getBytes());
       * os.close();
       */
   }
}
