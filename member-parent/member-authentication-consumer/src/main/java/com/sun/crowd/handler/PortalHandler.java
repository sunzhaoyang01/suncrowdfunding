package com.sun.crowd.handler;

import com.sun.crowd.api.MySQLRemoteService;
import com.sun.crowd.constant.CrowdConstant;
import com.sun.crowd.entity.vo.PortalTypeVO;
import com.sun.crowd.util.ResultEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class PortalHandler {

    @Resource
    private MySQLRemoteService mySQLRemoteService;

    @RequestMapping("/")
    public String showPortalPage(Model model){

        // 1.调用MySQLRemoteService提供的方法查询首页要显示的数据
        ResultEntity<List<PortalTypeVO>> resultEntity = mySQLRemoteService.getPortalTypeProjectDataRemote();

        // 2.检查查询结果
        String result = resultEntity.getResult();

        if(ResultEntity.SUCCESS.equals(result)) {

            // 3.获取查询结果数据
            List<PortalTypeVO> list = resultEntity.getData();

            // 4.存入模型
            model.addAttribute(CrowdConstant.ATTR_NAME_PORTAL_DATA, list);

        }

        return "portal";
    }

}
