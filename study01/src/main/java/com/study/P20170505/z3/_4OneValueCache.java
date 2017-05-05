package com.study.P20170505.z3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zd on 2017/5/5.
 */
public class _4OneValueCache {

    private final Integer num;
    private final Integer[] factors;

    public _4OneValueCache(Integer num, Integer[] factors) {
        this.num = num;
        this.factors = factors;
    }

    public Integer getNum() {
        return num;
    }

    public Integer[] getFactors(Integer num) {
        if (this.num == null || !this.num.equals(num)) return null;
        return Arrays.copyOf(factors, factors.length);
    }
}
