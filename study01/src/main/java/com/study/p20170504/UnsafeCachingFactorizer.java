package com.study.p20170504;

import javax.servlet.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by zd on 2017/5/4.
 */
//线程不安全
public class UnsafeCachingFactorizer implements Servlet {

    private AtomicReference<Integer> lastNum = new AtomicReference<>();
    private AtomicReference<Integer[]> lastFactors = new AtomicReference<>();

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        Integer size = res.getBufferSize();
        if(size.equals(lastNum.get())){
            Object factors = lastFactors.get();
            //执行factors 省略代码
        }else{
            Integer[] factors = factor(size);
            lastNum.set(size);
            lastFactors.set(factors);
            //执行factors 省略代码
        }
    }

    public Integer[] factor(long val) {
        List<Integer> list = new LinkedList<>();
        while (true) {
            for (int i = 2; i <= val; i++) {
                if (val % i == 0) {
                    list.add(i);
                    val = val / i;
                    if (val == 1) {
                        return list.toArray(new Integer[list.size()]);
                    }
                    break;
                }
            }
        }

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }


}
