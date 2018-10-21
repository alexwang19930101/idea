package com.wxy.sale.controller.handlers;

import com.wxy.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Map;

@SessionAttributes(value = {"userSession","address"})
@Controller
@RequestMapping("/hello")
public class HelloWorld {
    private static final String SUCCESS = "success";

    @RequestMapping("/testFileUpload")
    public String testFileUpload(@RequestParam("file") MultipartFile file){
        if (null != file) {
            System.out.println("OriginalFilename:" + file.getOriginalFilename());
            return SUCCESS;
        }
        else {
            System.out.println("OriginalFilename: null");
            return "failure";
        }
    }

    @RequestMapping("/testView")
    public String testView(){
        System.out.println("testView");
        return "helloView";
    }

    /**
     * 1.执行ModelAttribute注解修饰的方法：从数据库获取对象，放入Map中
     * 2.SpringMVC从Map中取出User对象，把表单请求参数赋值给User对象的对应属性
     * 3.SpringMVC把上述对象传入目标方法的参数
     *
     * @ModelAttribute 修饰方法Map的键必须和目标方法传入第一个参数类型的
     *                      首字母小写时名字对应
     */
    @ModelAttribute
    private void getUser(@RequestParam(value = "id",required = false) Integer id,Map<String,Object> map){
        System.out.println("ModelAttribute");
        if(null != id){
            User user = new User(1,"tom","123456","www@aa.com",15);
            System.out.println("从数据库获取一个对象："+user);
            map.put("user",user);
        }
    }

    @RequestMapping("/testModelAttribute")
    public String testModelAttribute(User user, Model model){
        model.addAttribute("a","a");
        System.out.println("修改："+user);
        return  SUCCESS;
    }

    @RequestMapping("/testSessionAttributes")
    public String testSessionAttributes(Map<String,Object> map){
        User user = new User();
        user.setUsername("a");
        map.put("userSession",user);
        map.put("str","TEST_STR");
        return SUCCESS;
    }

    @RequestMapping(value = "testMap")
    public String testMap(Map<String,Object> map){
        map.put("name",new String[]{"a","b"});
        return SUCCESS;
    }

    /**
     * ModelAndView可以包含视图和模型数据信息
     * SpringMVC会把Model的信息放入request域对象中
     */
    @RequestMapping(value = "/testModelAndView",method = RequestMethod.GET)
    public ModelAndView testModelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(SUCCESS);
        modelAndView.addObject("time", new Date());

        return modelAndView;
    }

    @RequestMapping(value = "/testpojo")
    public String testpojo(User user) {
        System.out.println(user);
        return SUCCESS;
    }

    @RequestMapping(value = "testCookieValue")
    public String testCookieValue(@CookieValue("JSESSIONID") String id) {
        System.out.println("testRequestParam:" + id);
        return SUCCESS;
    }

    /**
     * @RequestParam来映射请求参数 value = "password"   参数名
     * required = false     请求参数是否必须
     * defaultValue = ""    默认值
     */
    @RequestMapping(value = "testRequestParam")
    public String testRequestParam(@RequestParam(value = "username", required = false) String username,
                                   @RequestParam(value = "password", required = false, defaultValue = "") String pwd) {
        System.out.println("testRequestParam:" + username + "  " + pwd);
        return SUCCESS;
    }

    @RequestMapping(value = "/testRest", method = RequestMethod.POST)
    public String testRestPOST() {
        System.out.println("testRestPOST:");
        return SUCCESS;
    }

    @RequestMapping(value = "/testRest/{id}", method = RequestMethod.PUT)
    public String testRestPUT(@PathVariable("id") Integer id) {
        System.out.println("testRestPUT:" + id);
        return SUCCESS;
    }

    @RequestMapping(value = "/testRest/{id}", method = RequestMethod.DELETE)
    public String testRestDELETE(@PathVariable("id") Integer id) {
        System.out.println("testRestDELETE:" + id);
        return SUCCESS;
    }

    @RequestMapping(value = "/testRest/{id}", method = RequestMethod.GET)
    public String testRestGET(@PathVariable("id") Integer id) {
        System.out.println("testRestGET:" + id);
        return SUCCESS;
    }

    @RequestMapping(value = "/testPathVariable/{id}")
    public String testPathVariable(@PathVariable("id") Integer id) {
        System.out.println(id);
        return SUCCESS;
    }

    @RequestMapping("/helloworld")
    public String hello() {
        System.out.println("Hello" + " World");
        return SUCCESS;
    }

    /**
     * 指定使用method属性来规范请求方式
     *
     * @return
     */
    @RequestMapping(value = "testMethod", method = RequestMethod.POST)
    public String testMethod() {
        System.out.println("testMethod");
        return SUCCESS;
    }
}
