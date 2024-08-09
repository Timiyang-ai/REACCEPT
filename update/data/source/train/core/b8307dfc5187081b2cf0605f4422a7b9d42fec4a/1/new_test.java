@Test
    public void testInitialize() {

        Weld weld = new Weld();
        WeldContainer container = weld.initialize();

        MainTestBean mainTestBean = container.instance().select(MainTestBean.class).get();
        assertNotNull(mainTestBean);

        ParametersTestBean paramsBean = mainTestBean.getParametersTestBean();
        assertNotNull(paramsBean);
        assertNotNull(paramsBean.getParameters());

        weld.shutdown();
    }