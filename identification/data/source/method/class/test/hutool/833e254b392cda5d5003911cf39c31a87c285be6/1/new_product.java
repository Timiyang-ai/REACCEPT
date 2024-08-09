public static Process exec(String... cmds) {
		if (ArrayUtil.isEmpty(cmds)) {
			throw new NullPointerException("Command is empty !");
		}

		// 单条命令的情况
		if (1 == cmds.length) {
			final String cmd = cmds[0];
			if (StrUtil.isBlank(cmd)) {
				throw new NullPointerException("Command is empty !");
			}
			cmds = StrUtil.splitToArray(cmd, StrUtil.C_SPACE);
		}

		Process process;
		try {
			process = new ProcessBuilder(cmds).redirectErrorStream(true).start();
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
		return process;
	}