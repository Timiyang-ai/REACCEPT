  @Test
  public void newExecuteEvent_expectedResult() {
    // Set up arguments, as a combination of String and SkylarkPath
    ArrayList<Object> arguments = new ArrayList<>();
    arguments.add("argument 1");
    arguments.add(new DummyString());

    Map<String, String> commonEnv = ImmutableMap.of("key1", "val1", "key3", "val3");
    Map<String, String> customEnv = ImmutableMap.of("key2", "val2!", "key3", "val3!");

    WorkspaceLogProtos.WorkspaceEvent event =
        WorkspaceRuleEvent.newExecuteEvent(
                arguments,
                2042,
                commonEnv,
                customEnv,
                "outputDir",
                true,
                "my_rule",
                new DummyLocation())
            .getLogEvent();

    List<String> expectedArgs = Arrays.asList("argument 1", "dummy string");

    Map<String, String> expectedEnv =
        ImmutableMap.of(
            "key1", "val1",
            "key2", "val2!",
            "key3", "val3!");

    assertThat(event.getRule()).isEqualTo("my_rule");
    assertThat(event.getLocation()).isEqualTo("location being printed");

    WorkspaceLogProtos.ExecuteEvent executeEvent = event.getExecuteEvent();
    assertThat(executeEvent.getTimeoutSeconds()).isEqualTo(2042);
    assertThat(executeEvent.getQuiet()).isEqualTo(true);
    assertThat(executeEvent.getOutputDirectory()).isEqualTo("outputDir");
    assertThat(executeEvent.getArgumentsList()).isEqualTo(expectedArgs);
    assertThat(executeEvent.getEnvironmentMap()).isEqualTo(expectedEnv);
  }