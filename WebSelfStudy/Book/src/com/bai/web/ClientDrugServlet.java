package com.bai.web;

import com.bai.pojo.Drug;
import com.bai.pojo.Page;
import com.bai.service.DrugService;
import com.bai.service.impl.DrugServiceImpl;
import com.bai.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientDrugServlet extends BaseServlet {
    private DrugService drugService=new DrugServiceImpl();


    /**
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
//        System.out.println("经过前台的page");
//1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
//2 调用 BookService.page(pageNo，pageSize)：Page 对象
        Page<Drug> page = drugService.page(pageNo,pageSize);

        page.setUrl("client/clientDrugServlet?action=page");
//3 保存 Page 对象到 Request 域中
        req.setAttribute("page",page);
//4 请求转发到 pages/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

}
