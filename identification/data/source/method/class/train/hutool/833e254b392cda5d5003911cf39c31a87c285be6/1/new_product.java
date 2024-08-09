public static String execForStr(Charset charset, String... cmds) throws IORuntimeException {
		return getResult(exec(cmds), charset);
	}