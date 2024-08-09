public void contextInitialized(ServletContextEvent servletContextEvent) {

        if (null != beanManager) {
             JspApplicationContext jspAppContext = getJspApplicationContext(servletContextEvent);
             jspAppContext.addELResolver(beanManager.getELResolver());

             try {
                 Class weldClass = Class.forName("org.jboss.weld.el.WeldELContextListener");
                 WeldELContextListener welcl = ( WeldELContextListener ) weldClass.newInstance();
                 jspAppContext.addELContextListener(welcl);
             } catch (Exception e) {
                 logger.log(Level.WARNING,
                            CDILoggerInfo.CDI_COULD_NOT_CREATE_WELDELCONTEXTlISTENER,
                            new Object [] {e});
             }

            ( ( JspApplicationContextImpl ) jspAppContext ).setExpressionFactory(
                beanManager.wrapExpressionFactory(jspAppContext.getExpressionFactory()));
        }
    }