public static WorkspaceRuleEvent newExecuteEvent(
      Iterable<Object> args,
      Integer timeout,
      Map<String, String> commonEnvironment,
      Map<String, String> customEnvironment,
      String outputDirectory,
      boolean quiet,
      String ruleLabel,
      Location location) {

    WorkspaceLogProtos.ExecuteEvent.Builder e =
        WorkspaceLogProtos.ExecuteEvent.newBuilder()
            .setTimeoutSeconds(timeout.intValue())
            .setOutputDirectory(outputDirectory)
            .setQuiet(quiet);
    if (commonEnvironment != null) {
      e = e.putAllEnvironment(commonEnvironment);
    }
    if (customEnvironment != null) {
      e = e.putAllEnvironment(customEnvironment);
    }

    for (Object a : args) {
      e.addArguments(a.toString());
    }

    WorkspaceLogProtos.WorkspaceEvent.Builder result =
        WorkspaceLogProtos.WorkspaceEvent.newBuilder();
    result = result.setExecuteEvent(e.build());
    if (location != null) {
      result = result.setLocation(location.print());
    }
    if (ruleLabel != null) {
      result = result.setRule(ruleLabel);
    }
    return new WorkspaceRuleEvent(result.build());
  }