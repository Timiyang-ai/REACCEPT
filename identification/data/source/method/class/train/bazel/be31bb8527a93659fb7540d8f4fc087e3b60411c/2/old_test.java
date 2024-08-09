  @Test
  public void describeCommandError() throws Exception {
    String[] args = new String[40];
    args[0] = "some_command";
    for (int i = 1; i < args.length; i++) {
      args[i] = "arg" + i;
    }
    args[7] = "with spaces"; // Test embedded spaces in argument.
    args[9] = "*";           // Test shell meta characters.
    Map<String, String> env = new LinkedHashMap<>();
    env.put("FOO", "foo");
    env.put("PATH", "/usr/bin:/bin:/sbin");
    String cwd = "/my/working/directory";
    PlatformInfo executionPlatform =
        PlatformInfo.builder().setLabel(Label.parseAbsoluteUnchecked("//platform:exec")).build();
    String message =
        CommandFailureUtils.describeCommandError(
            false, Arrays.asList(args), env, cwd, executionPlatform);
    String verboseMessage =
        CommandFailureUtils.describeCommandError(
            true, Arrays.asList(args), env, cwd, executionPlatform);
    assertThat(message)
        .isEqualTo(
            "error executing command some_command arg1 "
                + "arg2 arg3 arg4 arg5 arg6 'with spaces' arg8 '*' arg10 "
                + "arg11 arg12 arg13 arg14 arg15 arg16 arg17 arg18 "
                + "arg19 arg20 arg21 arg22 arg23 arg24 arg25 arg26 "
                + "arg27 arg28 arg29 arg30 arg31 "
                + "... (remaining 8 argument(s) skipped)");
    assertThat(verboseMessage)
        .isEqualTo(
            "error executing command \n"
                + "  (cd /my/working/directory && \\\n"
                + "  exec env - \\\n"
                + "    FOO=foo \\\n"
                + "    PATH=/usr/bin:/bin:/sbin \\\n"
                + "  some_command arg1 arg2 arg3 arg4 arg5 arg6 'with spaces' arg8 '*' arg10 "
                + "arg11 arg12 arg13 arg14 arg15 arg16 arg17 arg18 "
                + "arg19 arg20 arg21 arg22 arg23 arg24 arg25 arg26 "
                + "arg27 arg28 arg29 arg30 arg31 arg32 arg33 arg34 "
                + "arg35 arg36 arg37 arg38 arg39)\n"
                + "Execution platform: //platform:exec");
  }