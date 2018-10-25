/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.test.base;

import com.xtaticzero.systems.business.logging.UserLogginService;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author xtati
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/**/business-context-test.xml"})
public abstract class BaseTest {
    
    @Autowired
    @Qualifier("userLogginService")
    protected UserLogginService userService;
    
    protected final Logger logger;
    public BaseTest() {
        logger = Logger.getLogger(getClass());
    }
}
