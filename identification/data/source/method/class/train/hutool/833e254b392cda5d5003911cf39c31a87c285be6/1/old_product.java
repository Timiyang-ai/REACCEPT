public static List<String> exec(Charset charset, String... cmds) throws IORuntimeException {
		Process process;
		try {
			// process = Runtime.getRuntime().exec(cmds);
			process = new ProcessBuilder(cmds).redirectErrorStream(true).start();
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
		return getResult(process, charset);
	}