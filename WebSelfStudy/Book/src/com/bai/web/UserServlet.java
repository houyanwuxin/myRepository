package com.bai.web;

import com.bai.pojo.User;
import com.bai.service.UserService;
import com.bai.service.impl.UserServiceImpl;
import com.bai.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.runners.Parameterized;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;


public class UserServlet extends BaseServlet {
    UserService userService=new UserServiceImpl();

    /**
     * 用户注销方法
     */
    public void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 移除Session对象中的用户信息
        request.getSession().removeAttribute("user");
        // 然后重定向到登录页面。或首页
        response.sendRedirect(request.getContextPath());
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=  req.getParameter("username");
        String password=  req.getParameter("password");

        User user = userService.loginUser(new User(username, password, null));
        if(user==null){
//            System.out.println("登录失败");
            req.setAttribute("mgr","用户名或密码错误");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else{
//            System.out.println("登录成功");
            req.getSession().setAttribute("user",user);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String email=req.getParameter("email");
        String code=req.getParameter("code");
//        判断验证码是否正确
//        先写死，abcde
        User user= WebUtils.copyParmToBean(req.getParameterMap(),new User());

        // 获取 Session 中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
// 删除 Session 中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        if(token!=null&&token.equalsIgnoreCase(code)){
            //判断用户是否可用
            if(userService.isExistsUsername(username)){
                System.out.println("用户名不可用");
                req.setAttribute("mgr","用户名已存在");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else {
                userService.registUser(new User(username,password,email));
                req.getSession().setAttribute("user",user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else {
            System.out.println("验证码错误，返回注册页面");
            req.setAttribute("mgr","验证码错误");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }

}
