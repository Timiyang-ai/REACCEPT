public void contextInitialized(ServletContextEvent sce) {

        if (null != beanManager) {
            JspApplicationContext jspAppContext = JspFactory.getDefaultFactory().
                getJspApplicationContext(sce.getServletContext());

             jspAppContext.addELResolver(beanManager.getELResolver());

             try {
                 Class weldClass = Class.forName("org.jboss.weld.el.WeldELContextListener");
                 WeldELContextListener welcl = (WeldELContextListener)weldClass.newInstance(); 
                 jspAppContext.addELContextListener(welcl);
             } catch (Exception e) {
                 _logger.log(Level.WARNING, "Could not create WeldELContextListener instance. ", e);
             }

            ((JspApplicationContextImpl)jspAppContext).setExpressionFactory(
                beanManager.wrapExpressionFactory(jspAppContext.getExpressionFactory()));
        }

    }