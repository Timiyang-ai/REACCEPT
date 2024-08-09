	@Test(expected = MandatoryModuleException.class)
	public void checkMandatoryModulesStarted_shouldThrowModuleExceptionIfAMandatoryModuleIsNotStarted() {
		//given
		assertThat(ModuleFactory.getStartedModules(), empty());
		
		GlobalProperty gp1 = new GlobalProperty("module1.mandatory", "true");
		Context.getAdministrationService().saveGlobalProperty(gp1);
		
		//when
		ModuleUtil.checkMandatoryModulesStarted();
		//then exception
	}