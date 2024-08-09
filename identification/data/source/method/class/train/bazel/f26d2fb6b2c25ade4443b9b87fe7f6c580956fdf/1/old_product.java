public static String describeCommandFailure(
      boolean verbose,
      Collection<String> commandLineElements,
      Map<String, String> env,
      String cwd) {

    String commandName = commandLineElements.iterator().next();
    // Extract the part of the command name after the last "/", if any.
    String shortCommandName = new File(commandName).getName();
    return shortCommandName
        + " failed: "
        + describeCommandError(verbose, commandLineElements, env, cwd);
  }