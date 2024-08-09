@Test
  public void testResumeGatewaySender_onMember() {

    Integer punePort = vm1.invoke(() -> createFirstLocatorWithDSId(1));

    Properties props = getDistributedSystemProperties();
    props.setProperty(MCAST_PORT, "0");
    props.setProperty(DISTRIBUTED_SYSTEM_ID, "1");
    props.setProperty(LOCATORS, "localhost[" + punePort + "]");
    setUpJmxManagerOnVm0ThenConnect(props);

    Integer nyPort = vm2.invoke(() -> createFirstRemoteLocator(2, punePort));

    vm3.invoke(() -> createCache(punePort));
    vm3.invoke(() -> createSender("ln", 2, false, 100, 400, false, false, null, true));

    vm3.invoke(() -> startSender("ln"));

    vm3.invoke(() -> verifySenderState("ln", true, false));

    vm3.invoke(() -> pauseSender("ln"));

    vm3.invoke(() -> verifySenderState("ln", true, true));

    final DistributedMember vm1Member = vm3.invoke(() -> getMember());
    pause(10000);
    String command = CliStrings.RESUME_GATEWAYSENDER + " --" + CliStrings.RESUME_GATEWAYSENDER__ID
        + "=ln --" + CliStrings.MEMBER + "=" + vm1Member.getId();
    CommandResult cmdResult = executeCommand(command);
    if (cmdResult != null) {
      String strCmdResult = commandResultToString(cmdResult);
      getLogWriter().info("testResumeGatewaySender stringResult : " + strCmdResult + ">>>>");
      assertEquals(Result.Status.OK, cmdResult.getStatus());
      assertTrue(strCmdResult.contains("is resumed on member"));
    } else {
      fail("testResumeGatewaySender failed as did not get CommandResult");
    }

    vm3.invoke(() -> verifySenderState("ln", true, false));
  }