/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.vista.util;

import com.xtaticzero.systems.base.util.FechaUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class CommonComboList {
    public static List<Integer> getYearList(int maxLimit){
        Integer yearCurrent =  FechaUtil.getCurrentYear();
        List<Integer> lstYears = new ArrayList<>();
        
        for (int i = 0; i <= maxLimit; i++) {
            lstYears.add(yearCurrent-i);
        }
        
        return lstYears;
        
    }
}
