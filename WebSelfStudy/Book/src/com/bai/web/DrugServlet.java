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
import java.util.List;

/**
 * Servlet implementation class DrugServlet
 */
public class DrugServlet extends BaseServlet {
//	private static final long serialVersionUID = 1L;

	private DrugService drugService=new DrugServiceImpl();

//	public DrugServlet() {
//		drugService = new DrugServiceImpl();
//		System.out.println("DrugServlet构造");
//	}

	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Drug> drugs = drugService.queryDrugs();

		request.setAttribute("drugs", drugs);

		request.getRequestDispatcher("/pages/manager/drug_manager.jsp").forward(request, response);
	}

	protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
// 1、获取请求的参数==封装成为 Drug 对象
		int pageNo= WebUtils.parseInt(req.getParameter("pageNo"),0);
		pageNo+=1;
		Drug drug = WebUtils.copyParmToBean(req.getParameterMap(),new Drug());
// 2、调用 DrugService.addDrug()保存图书
		drugService.addDrug(drug);
// 3、跳到图书列表页面
// /manager/drugServlet?action=list
// req.getRequestDispatcher("/manager/drugServlet?action=list").forward(req, resp)

		resp.sendRedirect(req.getContextPath() + "/manager/drugServlet?action=page&pageNo=" + pageNo);
	}


		protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
				IOException {
// 1、获取请求的参数 id，图书编程
			int id = WebUtils.parseInt(req.getParameter("id"), 0);
// 2、调用 drugService.deleteDrugById();删除图书
			drugService.deleteDrugById(id);
// 3、重定向回图书列表管理页面
// /drug/manager/drugServlet?action=list
			resp.sendRedirect(req.getContextPath() + "/manager/drugServlet?action=page&pageNo=" + req.getParameter("pageNo"));
		}
	protected void getDrug(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
//1 获取请求的参数图书编号
		int id = WebUtils.parseInt(req.getParameter("id"), 0);
//2 调用 drugService.queryDrugById 查询图书
		Drug drug = drugService.queryDrugById(id);
//3 保存到图书到 Request 域中
		req.setAttribute("drug", drug) ;
//4 请求转发到。pages/manager/drug_edit.jsp 页面
		req.getRequestDispatcher("/pages/manager/drug_edit.jsp").forward(req,resp);
	}

	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
// 1、获取请求的参数==封装成为 Drug 对象
		Drug drug = WebUtils.copyParmToBean(req.getParameterMap(),new Drug());
// 2、调用 DrugService.updateDrug( drug );修改图书
		drugService.updateDrug(drug);
// 3、重定向回图书列表管理页面
// 地址：/工程名/manager/drugServlet?action=list
		resp.sendRedirect(req.getContextPath() + "/manager/drugServlet?action=page&pageNo=" + req.getParameter("pageNo"));
	}

	/**
	 * 处理分页功能
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
//1 获取请求的参数 pageNo 和 pageSize
		int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
		int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
//2 调用 DrugService.page(pageNo，pageSize)：Page 对象
		Page<Drug> page = drugService.page(pageNo,pageSize);
		page.setUrl("manager/drugServlet?action=page");
//3 保存 Page 对象到 Request 域中
		req.setAttribute("page",page);
//4 请求转发到 pages/manager/drug_manager.jsp 页面
		req.getRequestDispatcher("/pages/manager/drug_manager.jsp").forward(req,resp);
	}

}
