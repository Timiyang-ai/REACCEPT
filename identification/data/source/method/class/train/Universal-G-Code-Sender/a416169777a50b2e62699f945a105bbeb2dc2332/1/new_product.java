public static void executeCustomGcode(final String str, BackendAPI backend) throws Exception {
        if (str == null) {
            return;
        }
        String command = MacroHelper.substituteValues(str, backend);
        command = command.replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "");
        final String[] parts = command.split(";");

        /* 
         * specifically NOT catching exceptions on gCode commands, let them pass to the invoking method
         * so the error handling is aligned to the UI that triggered it (i.e. GUI button press versus Pendant UI http request)
         */
		for (String cmd : parts) {
			backend.sendGcodeCommand(cmd);
		}
    }