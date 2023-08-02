/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.se1715.group4.gasstore.models.client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.se1715.group4.gasstore.dao.DAOOrder;
import com.se1715.group4.gasstore.dao.DAOProduct;
import com.se1715.group4.gasstore.dto.Cart;
import com.se1715.group4.gasstore.dto.Customer;
import com.se1715.group4.gasstore.dto.Item;
import com.se1715.group4.gasstore.dto.Order;
import com.se1715.group4.gasstore.dto.Product;
import com.se1715.group4.gasstore.dto.UserGoogle;
import com.se1715.group4.gasstore.util.Config;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author ADMIN
 */
public class PaymentsClientServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PaymentsClientServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PaymentsClientServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param req
     * @param resp
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        DAOOrder daoOrder = new DAOOrder();
        HttpSession session = request.getSession();
        try {
            String type = request.getParameter("req");
            if (type != null && type.equals("pay")) {
                int orderId = Integer.parseInt(request.getParameter("oid"));
                Order order = daoOrder.getOrdersByOrderID(orderId);
                double total_cost = order.getTotalMoney();
                String vnp_Version = "2.0.0";
                String vnp_Command = "pay";
                String vnp_OrderInfo = "" + orderId;
                String orderType = "billpayment";
                String vnp_TxnRef = orderId + "";
                String vnp_IpAddr = Config.getIpAddress(request);
                String vnp_TmnCode = Config.vnp_TmnCode;

                int amount = (int) (Math.round(total_cost) * 100);
                Map<String, String> vnp_Params = new HashMap<>();
                vnp_Params.put("vnp_Version", vnp_Version);
                vnp_Params.put("vnp_Command", vnp_Command);
                vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
                vnp_Params.put("vnp_Amount", String.valueOf(amount));
                vnp_Params.put("vnp_CurrCode", "VND");
                String bank_code = "NCB";
                if (bank_code != null && bank_code.isEmpty()) {
                    vnp_Params.put("vnp_BankCode", bank_code);
                }
                vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
                vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
                vnp_Params.put("vnp_OrderType", orderType);

                String locate = "vi";
                if (locate != null && !locate.isEmpty()) {
                    vnp_Params.put("vnp_Locale", locate);
                } else {
                    vnp_Params.put("vnp_Locale", "vn");
                }
                vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);
                vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

                Date dt = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                String dateString = formatter.format(dt);
                String vnp_CreateDate = dateString;
                String vnp_TransDate = vnp_CreateDate;
                vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

                //Build data to hash and querystring
                List fieldNames = new ArrayList(vnp_Params.keySet());
                Collections.sort(fieldNames);
                StringBuilder hashData = new StringBuilder();
                StringBuilder query = new StringBuilder();
                Iterator itr = fieldNames.iterator();
                while (itr.hasNext()) {
                    String fieldName = (String) itr.next();
                    String fieldValue = (String) vnp_Params.get(fieldName);
                    if ((fieldValue != null) && (fieldValue.length() > 0)) {
                        //Build hash data
                        hashData.append(fieldName);
                        hashData.append('=');
                        hashData.append(fieldValue);
                        //Build query
                        query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                        query.append('=');
                        query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                        if (itr.hasNext()) {
                            query.append('&');
                            hashData.append('&');
                        }
                    }
                }
                String queryUrl = query.toString();
                String vnp_SecureHash = Config.Sha256(Config.vnp_HashSecret + hashData.toString());
                queryUrl += "&vnp_SecureHashType=SHA256&vnp_SecureHash=" + vnp_SecureHash;
                String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
                request.setAttribute("code", "00");
                request.setAttribute("message", "success");
                request.setAttribute("data", paymentUrl);
                response.sendRedirect(paymentUrl);
            } else {

                String method = request.getParameter("method");

                Cart cart = null;
                Object o = session.getAttribute("cart");
                if (o != null) {
                    cart = (Cart) o;
                } else {
                    cart = new Cart();
                }

                Customer cus = (Customer) session.getAttribute("customer");
                if (cus != null) {
                    String address = request.getParameter("address");
                    String phone = request.getParameter("phone");
                    cus.setAddress(address);
                    cus.setPhone(phone);
                }
                if (cus == null) {
                    cus = (Customer) session.getAttribute("account");
                }

                int ship = Integer.parseInt(request.getParameter("shipper"));
                String req = request.getParameter("req");
                String note = request.getParameter("notes");

                if (method.equalsIgnoreCase("vnpay")) {
                    daoOrder.addOrder(cus, cart, ship, req, note, 0);
                    int id = daoOrder.getNewOrderId(cus.getCustomerID());
                    double total_cost = cart.getTotalMoney();
                    String vnp_Version = "2.0.0";
                    String vnp_Command = "pay";
                    String vnp_OrderInfo = "" + id;
                    String orderType = "billpayment";
                    String vnp_TxnRef = id + "";
                    String vnp_IpAddr = Config.getIpAddress(request);
                    String vnp_TmnCode = Config.vnp_TmnCode;

                    int amount = (int) (Math.round(total_cost) * 100);
                    Map<String, String> vnp_Params = new HashMap<>();
                    vnp_Params.put("vnp_Version", vnp_Version);
                    vnp_Params.put("vnp_Command", vnp_Command);
                    vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
                    vnp_Params.put("vnp_Amount", String.valueOf(amount));
                    vnp_Params.put("vnp_CurrCode", "VND");
                    String bank_code = "NCB";
                    if (bank_code != null && bank_code.isEmpty()) {
                        vnp_Params.put("vnp_BankCode", bank_code);
                    }
                    vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
                    vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
                    vnp_Params.put("vnp_OrderType", orderType);

                    String locate = "vi";
                    if (locate != null && !locate.isEmpty()) {
                        vnp_Params.put("vnp_Locale", locate);
                    } else {
                        vnp_Params.put("vnp_Locale", "vn");
                    }
                    vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);
                    vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

                    Date dt = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                    String dateString = formatter.format(dt);
                    String vnp_CreateDate = dateString;
                    String vnp_TransDate = vnp_CreateDate;
                    vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

                    //Build data to hash and querystring
                    List fieldNames = new ArrayList(vnp_Params.keySet());
                    Collections.sort(fieldNames);
                    StringBuilder hashData = new StringBuilder();
                    StringBuilder query = new StringBuilder();
                    Iterator itr = fieldNames.iterator();
                    while (itr.hasNext()) {
                        String fieldName = (String) itr.next();
                        String fieldValue = (String) vnp_Params.get(fieldName);
                        if ((fieldValue != null) && (fieldValue.length() > 0)) {
                            //Build hash data
                            hashData.append(fieldName);
                            hashData.append('=');
                            hashData.append(fieldValue);
                            //Build query
                            query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                            query.append('=');
                            query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                            if (itr.hasNext()) {
                                query.append('&');
                                hashData.append('&');
                            }
                        }
                    }
                    String queryUrl = query.toString();
                    String vnp_SecureHash = Config.Sha256(Config.vnp_HashSecret + hashData.toString());
                    queryUrl += "&vnp_SecureHashType=SHA256&vnp_SecureHash=" + vnp_SecureHash;
                    String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
                    request.setAttribute("code", "00");
                    request.setAttribute("message", "success");
                    request.setAttribute("data", paymentUrl);
                    response.sendRedirect(paymentUrl);
                }
                if (method.equalsIgnoreCase("delivery")) {
                    daoOrder.addOrder((Customer) cus, cart, ship, req, note, 2);
                    request.setAttribute("msg", "Đặt hàng thành công.");
                    session.removeAttribute("cart");
                    session.removeAttribute("size");
                    request.getRequestDispatcher("checkout.jsp").forward(request, response);
                }

            }
        } catch (Exception e) {
        }
    }

    //vui lòng tham khảo thêm tại code demo
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
