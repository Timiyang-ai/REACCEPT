@Test
	public void testInit() throws IOException {
		AgentConfig config = new AgentConfig();
		config.init();
		File homeDir = config.getHome().getDirectory();
		System.out.println("Home:" + homeDir.getAbsolutePath());
		
		config.saveAgentPidProperties("1000", "agent");
		String pid  = config.getAgentPidProperties("agent");
		assertThat(pid, is("1000"));
		
		config.saveAgentPidProperties("1001", "monitor");
		String monitorPid  = config.getAgentPidProperties("monitor");
		assertThat(monitorPid, is("1001"));

		assertThat(config.isTestMode(), is(false));

		System.setProperty("ngrinder.agent.home", "./tmp_agent_home");
		config.init();
		homeDir = config.getHome().getDirectory();
		assertTrue(homeDir.getAbsolutePath().contains("tmp_agent_home"));
		FileUtils.deleteDirectory(homeDir);
	}