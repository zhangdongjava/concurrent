package com.study.P20170505.z3;

import javax.servlet.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by zd on 2017/5/4.
 */
//线程安全
public class _4VolatileCacheFactorizer implements Servlet {

    //volatile 保证可见性
    private volatile  _4OneValueCache cache = new _4OneValueCache(null, null);


    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public synchronized void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        Integer num = Integer.valueOf(req.getParameter("num"));
        Integer[] factors = null;
        factors = cache.getFactors(num);
        if (factors == null) {
            factors = factor(num);
            cache = new _4OneValueCache(num, factors);
        }
        res.getWriter().write(Arrays.toString(factors));
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
