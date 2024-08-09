public static void executeCustomGcode(final String str, BackendAPI backend) {
        if (str == null) {
            return;
        }
        String command = MacroHelper.substituteValues(str, backend);
        command = command.replaceAll("(\\r\\n|\\n\\r|\\r|\\n)", "");
        final String[] parts = command.split(";");
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    for (String cmd : parts) {
                        backend.sendGcodeCommand(cmd);
                    }
                } catch (Exception ex) {
                    GUIHelpers.displayErrorDialog(ex.getMessage());
                }
            }
        });
    }