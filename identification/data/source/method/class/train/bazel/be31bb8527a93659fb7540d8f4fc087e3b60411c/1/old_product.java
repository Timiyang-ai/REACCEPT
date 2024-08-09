public static String describeCommandError(boolean verbose,
                                            Collection<String> commandLineElements,
                                            Map<String, String> env, String cwd) {
    CommandDescriptionForm form = verbose
        ? CommandDescriptionForm.COMPLETE
        : CommandDescriptionForm.ABBREVIATED;
    return "error executing command " + (verbose ? "\n  " : "")
        + describeCommand(form, commandLineElements, env, cwd);
  }