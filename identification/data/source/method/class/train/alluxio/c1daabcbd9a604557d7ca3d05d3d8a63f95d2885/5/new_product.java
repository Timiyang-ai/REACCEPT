public static Map<String, Command> loadCommands(FileSystemContext fsContext) {
    return CommandUtils.loadCommands(FileSystemShell.class.getPackage().getName(),
        new Class[] {FileSystemContext.class}, new Object[] {fsContext});
  }