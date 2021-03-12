package com.microsoft.lb;


import com.microsoft.lb.util.ResourceContext;

/**
 * Finds specified strings in a large text
 */
public class App {
    public static void main(String[] args) {
        new App().execute();
    }
    private ResourceContext resourceContext = new ResourceContext();

    public void execute(){
		System.out.println("Q size:" + resourceContext.getQueueSize());
    }
}
