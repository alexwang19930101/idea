package com.wxy.crm.controller;

import com.wxy.crm.pojo.BaseDict;
import com.wxy.crm.pojo.Customer;
import com.wxy.crm.pojo.QueryVo;
import com.wxy.crm.service.BaseDictService;
import com.wxy.crm.service.CustomerService;
import com.wxy.crm.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Value("${customer_source}")
    private String customerSource;
    @Value("${customer_industry}")
    private String customerIndustry;
    @Value("${customer_level}")
    private String customerLevel;

    @Autowired
    private BaseDictService baseDictService;

    @Autowired
    private CustomerService customerService;
    /**
     * 显示我们customer.jsp页面出来
     */
    @RequestMapping("/list.action")
    public ModelAndView getCustomerList(QueryVo queryVo) {
        System.out.println(queryVo);
        ModelAndView mv = new ModelAndView();

        List<BaseDict> customerSourceList = baseDictService.getCustomerSourceList(customerSource);
        List<BaseDict> customerIndustryList = baseDictService.getCustomerSourceList(customerIndustry);
        List<BaseDict> customerLevelList = baseDictService.getCustomerSourceList(customerLevel);

        Page<Customer> customerPage = customerService.getCustomerPage(queryVo);
        //列表数据
        mv.addObject("page",customerPage);
        //选择条件数据
        mv.addObject("fromType", customerSourceList);
        mv.addObject("industryType", customerIndustryList);
        mv.addObject("levelType", customerLevelList);
        //选择条件回显
        mv.addObject("custName", queryVo.getCustName());
        mv.addObject("custSource", queryVo.getCustSource());
        mv.addObject("custIndustry", queryVo.getCustIndustry());
        mv.addObject("custLevel", queryVo.getCustLevel());
        //设置view名称
        mv.setViewName("customer");
        return mv;
    }

    /**
     * 修改信息回显
     * @param id    修改客户id
     * @return
     */
    @RequestMapping(value = "/edit.action")
    @ResponseBody
    public Customer editCustomer(Integer id){
        Customer customer = customerService.getCustomerById(id);
        return customer;
    }

    @RequestMapping("/update.action")
    @ResponseBody
    public String updateCustomer(Customer customer){
        customerService.updateCustomer(customer);
        return "success";
    }

    @RequestMapping("/delete.action")
    @ResponseBody
    public String deleteCustomer(Integer id){
        customerService.deleteById(id);

        return "success";
    }
}