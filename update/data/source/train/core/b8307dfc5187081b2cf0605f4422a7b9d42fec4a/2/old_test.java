@Test
    public void testInitialize() {

        WeldContainer weld = new Weld().initialize();

        MainTestBean mainTestBean = weld.instance().select(MainTestBean.class).get();
        assertNotNull(mainTestBean);

        ParametersTestBean paramsBean = mainTestBean.getParametersTestBean();
        assertNotNull(paramsBean);
        assertNotNull(paramsBean.getParameters());

        shutdownManager(weld);
    }