public static List<String> exec(String... cmds) throws IORuntimeException {
		return exec(CharsetUtil.defaultCharset(), cmds);
	}