package com.cunjunwang.hospital.emp.init_version_2016;

import java.util.Comparator;

/**
 * Created by CunjunWang on 16/11/1.
 */
public class NumberSorter implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        if(o1 instanceof Integer && o2 instanceof Integer){
            return (Integer) o1 - (Integer) o2;
        }
        else {
            return Double.compare((Double) o1, (Double) o2);
        }
    }
}
