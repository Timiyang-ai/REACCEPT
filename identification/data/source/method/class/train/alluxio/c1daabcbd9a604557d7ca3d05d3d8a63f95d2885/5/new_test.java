  @Test
  public void loadCommands() {
    Map<String, Command> map =
        FileSystemShellUtils.loadCommands(FileSystemContext.create(ServerConfiguration.global()));

    String pkgName = Command.class.getPackage().getName();
    Reflections reflections = new Reflections(pkgName);
    Set<Class<? extends Command>> cmdSet = reflections.getSubTypesOf(Command.class);
    for (Map.Entry<String, Command> entry : map.entrySet()) {
      assertEquals(entry.getValue().getCommandName(), entry.getKey());
      assertEquals(cmdSet.contains(entry.getValue().getClass()), true);
    }

    int expectSize = 0;
    for (Class<? extends Command> cls : cmdSet) {
      if (cls.getPackage().getName()
          .equals(FileSystemShell.class.getPackage().getName() + ".command")
          && !Modifier.isAbstract(cls.getModifiers())) {
        expectSize++;
      }
    }
    assertEquals(expectSize, map.size());
  }