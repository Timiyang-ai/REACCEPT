	@Test
	public void getMandatoryModules_shouldReturnMandatoryModuleIds() {
		//given
		GlobalProperty gp1 = new GlobalProperty("firstmodule.mandatory", "true");
		GlobalProperty gp2 = new GlobalProperty("secondmodule.mandatory", "false");
		
		Context.getAdministrationService().saveGlobalProperty(gp1);
		Context.getAdministrationService().saveGlobalProperty(gp2);
		
		//when
		//then
		assertThat(ModuleUtil.getMandatoryModules(), contains("firstmodule"));
	}