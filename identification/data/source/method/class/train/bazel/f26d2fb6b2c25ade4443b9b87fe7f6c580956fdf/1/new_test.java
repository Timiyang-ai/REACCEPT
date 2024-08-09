  @Test
  public void describeCommandFailure() throws Exception {
    String[] args = new String[3];
    args[0] = "/bin/sh";
    args[1] = "-c";
    args[2] = "echo Some errors 1>&2; echo Some output; exit 42";
    Map<String, String> env = new LinkedHashMap<>();
    env.put("FOO", "foo");
    env.put("PATH", "/usr/bin:/bin:/sbin");
    String cwd = null;
    PlatformInfo executionPlatform =
        PlatformInfo.builder().setLabel(Label.parseAbsoluteUnchecked("//platform:exec")).build();
    String message =
        CommandFailureUtils.describeCommandFailure(
            false, Arrays.asList(args), env, cwd, executionPlatform);
    String verboseMessage =
        CommandFailureUtils.describeCommandFailure(
            true, Arrays.asList(args), env, cwd, executionPlatform);
    assertThat(message)
        .isEqualTo(
            "sh failed: error executing command "
                + "/bin/sh -c 'echo Some errors 1>&2; echo Some output; exit 42'");
    assertThat(verboseMessage)
        .isEqualTo(
            "sh failed: error executing command \n"
                + "  (exec env - \\\n"
                + "    FOO=foo \\\n"
                + "    PATH=/usr/bin:/bin:/sbin \\\n"
                + "  /bin/sh -c 'echo Some errors 1>&2; echo Some output; exit 42')\n"
                + "Execution platform: //platform:exec");
  }