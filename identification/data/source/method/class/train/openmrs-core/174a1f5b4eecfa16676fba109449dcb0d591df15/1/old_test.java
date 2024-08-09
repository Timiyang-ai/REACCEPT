@Test
	public void startModule_shouldCreateDwrModulesXmlIfNotExists() throws Exception {
		partialMockWebModuleUtilForMessagesTests();
		
		// create dummy module and start it
		Module mod = buildModuleForMessageTest();
		ModuleFactory.getStartedModulesMap().put(mod.getModuleId(), mod);
		
		ServletContext servletContext = mock(ServletContext.class);
		String realPath = servletContext.getRealPath("");
		if (realPath == null)
			realPath = System.getProperty("user.dir");
		
		// manually delete dwr-modules.xml 
		File f = new File(realPath + "/WEB-INF/dwr-modules.xml");
		f.delete();
		
		// start the dummy module
		WebModuleUtil.startModule(mod, servletContext, true);
		
		// test if dwr-modules.xml is created
		assertTrue(f.exists());
		
		ModuleFactory.getStartedModulesMap().clear();
	}