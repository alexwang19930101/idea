package com.wxy.servlet;

import com.wxy.service.SecKillRedis;
import com.wxy.service.SecKillRedisByScript;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * create by pinkill on ${date}
 */
public class SecKillServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String userID = new Random().nextInt(5000)+"";
        String prodID = request.getParameter("prodid");

        boolean ifSuccess = SecKillRedisByScript.doSecKill(userID,prodID);
            response.getWriter().print(ifSuccess+"");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
