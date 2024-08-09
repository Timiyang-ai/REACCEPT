@Test
  public void testStartGatewaySender_ErrorConditions() {

    Integer punePort = (Integer) vm1.invoke(() -> createFirstLocatorWithDSId(1));

    Properties props = getDistributedSystemProperties();
    props.setProperty(MCAST_PORT, "0");
    props.setProperty(DISTRIBUTED_SYSTEM_ID, "1");
    props.setProperty(LOCATORS, "localhost[" + punePort + "]");
    setUpJmxManagerOnVm0ThenConnect(props);

    Integer nyPort = (Integer) vm2.invoke(() -> createFirstRemoteLocator(2, punePort));

    vm3.invoke(() -> createCache(punePort));
    vm3.invoke(() -> createSender("ln", 2, false, 100, 400, false, false, null, true));

    final DistributedMember vm1Member = (DistributedMember) vm3.invoke(() -> getMember());

    String command = CliStrings.START_GATEWAYSENDER + " --" + CliStrings.START_GATEWAYSENDER__ID
        + "=ln --" + CliStrings.START_GATEWAYSENDER__MEMBER + "=" + vm1Member.getId() + " --"
        + CliStrings.START_GATEWAYSENDER__GROUP + "=SenserGroup1";
    CommandResult cmdResult = executeCommandWithIgnoredExceptions(command);
    if (cmdResult != null) {
      String strCmdResult = commandResultToString(cmdResult);
      getLogWriter().info("testStartGatewaySender stringResult : " + strCmdResult + ">>>>");
      assertEquals(Result.Status.ERROR, cmdResult.getStatus());
      assertTrue(strCmdResult.contains(CliStrings.PROVIDE_EITHER_MEMBER_OR_GROUP_MESSAGE));
    } else {
      fail("testStartGatewaySender failed as did not get CommandResult");
    }
  }