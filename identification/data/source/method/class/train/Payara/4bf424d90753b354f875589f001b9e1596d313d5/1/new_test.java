	@Test
    public void testcontextInitialized() throws Exception {
        EasyMockSupport mockSupport = new EasyMockSupport();

        ELResolver elResolver = mockSupport.createMock(ELResolver.class);

        ExpressionFactory expressionFactory = mockSupport.createMock(ExpressionFactory.class);
        StandardContext servletContext = new StandardContext();
        servletContext.getServletContext();

        ServletContextEvent servletContextEvent = mockSupport.createMock( ServletContextEvent.class );
        BeanManager beanManager = mockSupport.createMock( BeanManager.class );
        JspApplicationContextImpl jspApplicationContext = new JspApplicationContextImpl(servletContext);

        expect(beanManager.getELResolver()).andReturn(elResolver);
        expect( beanManager.wrapExpressionFactory(isA(ExpressionFactory.class))).andReturn(expressionFactory);

        mockSupport.replayAll();

        WeldContextListener weldContextListener = getWeldContextListener(beanManager, jspApplicationContext);
        weldContextListener.contextInitialized( servletContextEvent );

        assertSame( expressionFactory, jspApplicationContext.getExpressionFactory() );
        validateJspApplicationContext( jspApplicationContext, elResolver );

        mockSupport.verifyAll();
        mockSupport.resetAll();

    }