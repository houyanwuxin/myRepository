package com.bai.web;

import com.bai.pojo.Cart;
import com.bai.pojo.CartItem;
import com.bai.pojo.Drug;
import com.bai.service.DrugService;
import com.bai.service.impl.DrugServiceImpl;
import com.bai.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet {
    private DrugService drugService=new DrugServiceImpl();
    /**
     * 加入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        // 获取请求的参数 商品编号
//        System.out.println("加入购物车");
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
// 调用 bookService.queryBookById(id):Book 得到图书的信息
        Drug drug = drugService.queryDrugById(id);
// 把图书信息，转换成为 CartItem 商品项
        CartItem cartItem = new CartItem(drug.getId(),drug.getName(),1,drug.getPrice(),drug.getPrice());
// 调用 Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);
        System.out.println("请求头 Referer 的值：" + req.getHeader("Referer"));
// 重定向回原来商品所在的地址页面
        resp.sendRedirect(req.getHeader("Referer"));

    }

    /**
     * 删除商品项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException{
// 获取商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
// 获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
// 删除 了购物车商品项
            cart.deleteItem(id);
// 重定向回原来购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

/**
 * 清空购物车
 * @param req
 * @param resp
}
 * @throws ServletException
 * @throws IOException
 */
        protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
                IOException{
        // 1 获取购物车对象
            Cart cart = (Cart) req.getSession().getAttribute("cart");
            if (cart != null) {
        // 清空购物车
                cart.clear();
        // 重定向回原来购物车展示页面
                resp.sendRedirect(req.getHeader("Referer"));
            }
        }

    /**
     * 修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException{
// 获取请求的参数 商品编号 、商品数量
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        // 获取 Cart 购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
// 修改商品数量
            cart.updateCount(id,count);
// 重定向回原来购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
