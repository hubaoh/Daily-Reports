package cn.bd.dailyReport.controller;

import cn.bd.dailyReport.model.User;
import cn.bd.dailyReport.service.LoginService;
import cn.bd.dailyReport.utils.messages.Message;
import cn.bd.dailyReport.utils.messages.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    @Qualifier(value = "loginService")
    private LoginService loginService;

//    /**
//     * 登陆
//     *
//     * @param model   接收登陆者的信息
//     * @param session 存贮登陆者的信息
//     * @return ModelAndView
//      */
//    @RequestMapping("/loginer")
//    public ModelAndView login(User model, HttpSession session) {
//
//        ModelAndView mv = new ModelAndView();
//        User user = loginService.login(model.getUserName(), model.getPassword());
//        if (user != null) {
//            session.setAttribute("loginer", user);
//            mv.setViewName("redirect:main");
//
//        } else {
//            mv.addObject("error", "密码或者用户名输入不正确！");
//            mv.setViewName("error");
//        }
//
//        return mv;
//    }

    /**
     * 登陆
     *
     * @param session 存贮登陆者的信息
     * @return ModelAndView
     */
    @ResponseBody
    @RequestMapping(value = "/currentUser", method = RequestMethod.GET)
    public ResponseEntity<Message> login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpSession session) {

        User model = loginService.login(userName, password);
        if (model != null) {
            session.setAttribute("loginer", model);
            return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_SUCCESS,model), HttpStatus.OK);
        } else {
            return new ResponseEntity<Message>(new Message(MessageType.MSG_TYPE_ERROR), HttpStatus.OK);
        }

    }

    /**
     * 退出登录
     *
     * @param mv
     * @param session
     * @return ModelAndView
     */
    @RequestMapping("/logout")
    public ModelAndView logout(ModelAndView mv, HttpSession session) {

        session.removeAttribute("loginer");
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping("/system")
    public ModelAndView main(ModelAndView mv) {
        mv.setViewName("main");
        return mv;
    }


}
