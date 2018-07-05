package cn.com.infohold.controller;

import bdp.commons.dataservice.param.ExecuteBatchSqlBean;
import bdp.commons.dataservice.param.InsertBean;
import bdp.commons.dataservice.param.QueryBean;
import bdp.commons.dataservice.ret.RetBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.infohold.service.IDataInsertService;
import cn.com.infohold.service.IDataQueryService;
import cn.com.infohold.util.AnalysisUtil;
import lombok.extern.log4j.Log4j2;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mojiaxing
 * @since 2017-08-03
 */
@RestController
@Log4j2
public class DataInsertController {

    @Autowired
    IDataInsertService dataInsertServiceImpl;

    @RequestMapping(value = "/insert")
    public RetBean dataInsert(@RequestParam String param) throws Exception {
        RetBean ret = new RetBean();
        try {
            InsertBean ib = AnalysisUtil.toQueryBean(param, InsertBean.class);
            ret = dataInsertServiceImpl.insertByJson(ib);
        } catch (Exception ex) {
            ret.setRet_code("-1");
            ret.setRet_message(ex.getLocalizedMessage());
            log.error(ex);
            throw ex;
        }
        return ret;
    }
    
    @RequestMapping(value = "/insertAuth")
    public RetBean insertAuth() throws Exception {
        RetBean ret = new RetBean();
        try {
            ret = dataInsertServiceImpl.insertAuth();
        } catch (Exception ex) {
            ret.setRet_code("-1");
            ret.setRet_message(ex.getLocalizedMessage());
            log.error(ex);
            throw ex;
        }
        return ret;
    }
    
    @RequestMapping(value = "/insertBatch")
    public RetBean insertBatch(@RequestParam String param) throws Exception {
        RetBean ret = new RetBean();
        try {
        	ExecuteBatchSqlBean executeBySqlBeans = AnalysisUtil.toQueryBean(param, ExecuteBatchSqlBean.class);
            ret = dataInsertServiceImpl.insertBatch(executeBySqlBeans);
             
        } catch (Exception ex) {
            ret.setRet_code("-1");
            ret.setRet_message(ex.getLocalizedMessage());
            log.error(ex);
            throw ex;
        }
        return ret;
    }
}
