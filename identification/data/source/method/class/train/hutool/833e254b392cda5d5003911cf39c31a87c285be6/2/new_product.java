public static Process exec(String[] envp, String... cmds) {
		return exec(envp, null, cmds);
	}