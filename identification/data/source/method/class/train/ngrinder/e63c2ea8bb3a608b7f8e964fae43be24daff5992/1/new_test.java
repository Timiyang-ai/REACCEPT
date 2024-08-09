@Test
	public void testAgentConfigInitialization() throws IOException {
		// Given
		AgentConfig config = new AgentConfig();
		config.init(true);
		File homeDir = config.getHome().getDirectory();
		// When
		config.saveAgentPidProperties("1000", "agent");
		String pid = config.getAgentPidProperties("agent");
		// Then
		assertThat(pid).isEqualTo("1000");

		//  When
		config.saveAgentPidProperties("1001", "monitor");
		String monitorPid = config.getAgentPidProperties("monitor");

		// Then
		assertThat(monitorPid).isEqualTo("1001");
		assertThat(config.isTestMode()).isEqualTo(false);

		// When
		System.setProperty("ngrinder.agent.home", "./tmp_agent_home");
		config.init(true);
		// Then
		homeDir = config.getHome().getDirectory();
		assertThat(homeDir.getAbsolutePath()).contains(File.separator + "tmp_agent_home");
		FileUtils.deleteDirectory(homeDir);
		System.setProperty("ngrinder.agent.home", "");

	}