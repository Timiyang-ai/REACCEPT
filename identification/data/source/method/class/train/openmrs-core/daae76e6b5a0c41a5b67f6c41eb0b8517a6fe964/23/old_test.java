	@Test
	public void parse_shouldParseValidXmlConfigCreatedFromInputStream() throws IOException {

		File moduleFile = new File(getClass().getClassLoader().getResource(LOGIC_MODULE_PATH).getPath());

		ModuleFileParser parser = new ModuleFileParser(new FileInputStream(moduleFile));

		Module module = parser.parse();

		assertThat(module.getModuleId(), is("logic"));
		assertThat(module.getVersion(), is("0.2"));
		assertThat(module.getPackageName(), is("org.openmrs.logic"));
		assertThat(module.getActivatorName(), is("org.openmrs.logic.LogicModuleActivator"));
		assertThat(module.getMappingFiles().size(), is(1));
		assertThat(module.getMappingFiles(), hasItems("LogicRuleToken.hbm.xml"));
	}