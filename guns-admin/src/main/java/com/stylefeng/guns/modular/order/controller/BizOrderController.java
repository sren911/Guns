package com.stylefeng.guns.modular.order.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.BizOrder;
import com.stylefeng.guns.modular.order.service.IBizOrderService;

/**
 * 订单管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-17 11:43:11
 */
@Controller
@RequestMapping("/bizOrder")
public class BizOrderController extends BaseController {

    private String PREFIX = "/order/bizOrder/";

    @Autowired
    private IBizOrderService bizOrderService;

    /**
     * 跳转到订单管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizOrder.html";
    }

    /**
     * 跳转到添加订单管理
     */
    @RequestMapping("/bizOrder_add")
    public String bizOrderAdd() {
        return PREFIX + "bizOrder_add.html";
    }

    /**
     * 跳转到修改订单管理
     */
    @RequestMapping("/bizOrder_update/{bizOrderId}")
    public String bizOrderUpdate(@PathVariable Integer bizOrderId, Model model) {
        BizOrder bizOrder = bizOrderService.selectById(bizOrderId);
        model.addAttribute("item",bizOrder);
        LogObjectHolder.me().set(bizOrder);
        return PREFIX + "bizOrder_edit.html";
    }

    /**
     * 获取订单管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return bizOrderService.selectList(null);
    }

    /**
     * 新增订单管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizOrder bizOrder) {
        bizOrderService.insert(bizOrder);
        return SUCCESS_TIP;
    }

    /**
     * 删除订单管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizOrderId) {
        bizOrderService.deleteById(bizOrderId);
        return SUCCESS_TIP;
    }

    /**
     * 修改订单管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizOrder bizOrder) {
        bizOrderService.updateById(bizOrder);
        return SUCCESS_TIP;
    }

    /**
     * 订单管理详情
     */
    @RequestMapping(value = "/detail/{bizOrderId}")
    @ResponseBody
    public Object detail(@PathVariable("bizOrderId") Integer bizOrderId) {
        return bizOrderService.selectById(bizOrderId);
    }
}
