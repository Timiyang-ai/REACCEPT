public static String describeCommandError(
      boolean verbose,
      Collection<String> commandLineElements,
      Map<String, String> env,
      String cwd,
      @Nullable PlatformInfo executionPlatform) {

    CommandDescriptionForm form = verbose
        ? CommandDescriptionForm.COMPLETE
        : CommandDescriptionForm.ABBREVIATED;

    StringBuilder output = new StringBuilder();
    output.append("error executing command ");
    if (verbose) {
      output.append("\n  ");
    }
    output.append(
        describeCommand(form, /* prettyPrintArgs= */ false, commandLineElements, env, cwd));
    if (verbose && executionPlatform != null) {
      output.append("\n");
      output.append("Execution platform: ").append(executionPlatform.label());
    }
    return output.toString();
  }