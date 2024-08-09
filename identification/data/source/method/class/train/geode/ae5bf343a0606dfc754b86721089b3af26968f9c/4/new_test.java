@Test
  public void testPauseGatewaySender_onMember() {

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

    final DistributedMember vm1Member = vm3.invoke(() -> getMember());
    pause(10000);
    String command = CliStrings.PAUSE_GATEWAYSENDER + " --" + CliStrings.PAUSE_GATEWAYSENDER__ID
        + "=ln --" + CliStrings.MEMBER + "=" + vm1Member.getId();
    CommandResult cmdResult = executeCommand(command);
    if (cmdResult != null) {
      String strCmdResult = commandResultToString(cmdResult);
      getLogWriter().info("testPauseGatewaySender stringResult : " + strCmdResult + ">>>>");
      assertEquals(Result.Status.OK, cmdResult.getStatus());
      assertTrue(strCmdResult.contains("is paused on member"));
    } else {
      fail("testPauseGatewaySender failed as did not get CommandResult");
    }

    vm3.invoke(() -> verifySenderState("ln", true, true));
  }