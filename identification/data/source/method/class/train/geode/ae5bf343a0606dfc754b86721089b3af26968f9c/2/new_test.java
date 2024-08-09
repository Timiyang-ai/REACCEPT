@Test
  public void testStartGatewayReceiver_MultipleGroup() {

    VM puneLocator = Host.getLocator();
    int punePort = puneLocator.invoke(() -> getLocatorPort());

    Properties props = getDistributedSystemProperties();
    props.setProperty(MCAST_PORT, "0");
    props.setProperty(LOCATORS, "localhost[" + punePort + "]");
    setUpJmxManagerOnVm0ThenConnect(props);

    Integer nyPort = vm2.invoke(() -> createFirstRemoteLocator(2, punePort));

    vm3.invoke(() -> createReceiverWithGroup(punePort, "RG1"));
    vm4.invoke(() -> createReceiverWithGroup(punePort, "RG1"));
    vm5.invoke(() -> createReceiverWithGroup(punePort, "RG1, RG2"));
    vm6.invoke(() -> createReceiverWithGroup(punePort, "RG1, RG2"));
    vm7.invoke(() -> createReceiverWithGroup(punePort, "RG3"));

    vm3.invoke(() -> verifyReceiverState(false));
    vm4.invoke(() -> verifyReceiverState(false));
    vm5.invoke(() -> verifyReceiverState(false));
    vm6.invoke(() -> verifyReceiverState(false));
    vm7.invoke(() -> verifyReceiverState(false));

    pause(10000);
    String command = CliStrings.START_GATEWAYRECEIVER + " --" + CliStrings.GROUP + "=RG1,RG2";
    CommandResult cmdResult = executeCommand(command);
    if (cmdResult != null) {
      String strCmdResult = commandResultToString(cmdResult);
      getLogWriter().info("testStartGatewayReceiver_Group stringResult : " + strCmdResult + ">>>>");
      assertEquals(Result.Status.OK, cmdResult.getStatus());

      TabularResultData resultData = (TabularResultData) cmdResult.getResultData();
      List<String> status = resultData.retrieveAllValues("Result");
      assertEquals(4, status.size());
      assertFalse(status.contains("Error"));
      assertTrue(status.contains("OK"));
    } else {
      fail("testStartGatewayReceiver failed as did not get CommandResult");
    }

    vm3.invoke(() -> verifyReceiverState(true));
    vm4.invoke(() -> verifyReceiverState(true));
    vm5.invoke(() -> verifyReceiverState(true));
    vm6.invoke(() -> verifyReceiverState(true));
    vm7.invoke(() -> verifyReceiverState(false));
  }