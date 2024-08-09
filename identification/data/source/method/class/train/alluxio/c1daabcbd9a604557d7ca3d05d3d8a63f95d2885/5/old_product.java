public static Map<String, Command> loadCommands(FileSystem fileSystem) {
    return CommandUtils.loadCommands(FileSystemShell.class.getPackage().getName(),
        new Class[] {FileSystem.class}, new Object[] {fileSystem});
  }