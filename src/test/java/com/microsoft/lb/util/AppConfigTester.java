package com.microsoft.lb.util;

import com.microsoft.lb.node.api.NodeFactory;
import com.microsoft.lb.node.api.SimpleExecutorNodeFactory;
import junit.framework.TestCase;
import org.junit.Test;

public class AppConfigTester {
    @Test
    public void testNodeFactory(){
        AppConfig ac = new AppConfig();
        NodeFactory nf = ac.getNodeFactory();
        TestCase.assertNotNull(nf);
        TestCase.assertEquals(SimpleExecutorNodeFactory.class, nf.getClass());
    }
}
