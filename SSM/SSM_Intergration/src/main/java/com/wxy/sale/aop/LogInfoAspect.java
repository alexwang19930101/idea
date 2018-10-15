package com.wxy.sale.aop;

import com.wxy.bean.Mylog;
import com.wxy.bean.Product;
import com.wxy.sale.dao.MylogMapper;
import com.wxy.sale.service.IMylogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
public class LogInfoAspect {
    @Autowired
    private IMylogService mylogService;

    @Pointcut(value = "execution(* com.wxy.sale.service.ProductServiceImpl.insert*(..))")
    public void insertPointCut() {
    }

    @Pointcut(value = "execution(* com.wxy.sale.service.ProductServiceImpl.delete*(..))")
    public void deletePointCut() {
    }

    @Pointcut(value = "execution(* com.wxy.sale.service.ProductServiceImpl.update*(..))")
    public void updatePointCut() {
    }

    @AfterReturning(value = "insertPointCut() || deletePointCut() || updatePointCut()", returning = "result")
    public void afterUpdateProduct(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        Object[] params = joinPoint.getArgs();
        System.out.println("目标方法的参数="+params[0]);
        System.out.println("目标方法执行完的返回值="+result);

        Long productId = 0L;
        if(params[0] instanceof Long) {
            productId = (Long)params[0];
        } else if(params[0] instanceof Product) {
            productId = ((Product)params[0]).getId();
        }

        Mylog mylog = new Mylog();
        mylog.setProductId(productId);
        mylog.setCreatetime(new Date());
        mylog.setDescription("商品信息由方法"+methodName+"修改，商品id为="+productId);
        mylogService.insert(mylog);
    }
}
