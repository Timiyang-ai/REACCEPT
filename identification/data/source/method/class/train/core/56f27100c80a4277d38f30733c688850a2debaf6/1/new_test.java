@Test
   public void testInitialize()
   {

      WeldContainer weld = new Weld().initialize();

      MainTestBean mainTestBean = weld.instance().select(MainTestBean.class).get();
      Assert.assertNotNull(mainTestBean);

      ParametersTestBean paramsBean = mainTestBean.getParametersTestBean();
      Assert.assertNotNull(paramsBean);
      Assert.assertNotNull(paramsBean.getParameters());

      shutdownManager(weld.getBeanManager());
   }