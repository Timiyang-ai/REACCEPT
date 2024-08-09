@Category(FlakyTest.class) // GEODE-1355
  @Test
  public void testCreateGatewayReceiver_onMultipleMembers() {
    VM puneLocator = Host.getLocator();
    int dsIdPort = puneLocator.invoke(this::getLocatorPort);
    propsSetUp(dsIdPort);
    vm2.invoke(() -> createFirstRemoteLocator(2, dsIdPort));

    vm3.invoke(() -> createCache(dsIdPort));
    vm4.invoke(() -> createCache(dsIdPort));
    vm5.invoke(() -> createCache(dsIdPort));

    final DistributedMember vm3Member = vm3.invoke(this::getMember);
    final DistributedMember vm4Member = vm4.invoke(this::getMember);

    String command =
        CliStrings.CREATE_GATEWAYRECEIVER + " --" + CliStrings.CREATE_GATEWAYRECEIVER__MANUALSTART
            + "=true" + " --" + CliStrings.CREATE_GATEWAYRECEIVER__BINDADDRESS + "=localhost"
            + " --" + CliStrings.CREATE_GATEWAYRECEIVER__STARTPORT + "=10000" + " --"
            + CliStrings.CREATE_GATEWAYRECEIVER__ENDPORT + "=11000" + " --"
            + CliStrings.CREATE_GATEWAYRECEIVER__MAXTIMEBETWEENPINGS + "=100000" + " --"
            + CliStrings.CREATE_GATEWAYRECEIVER__SOCKETBUFFERSIZE + "=512000" + " --"
            + CliStrings.MEMBER + "=" + vm3Member.getId() + "," + vm4Member.getId();
    CommandResult cmdResult = executeCommand(command);
    if (cmdResult != null) {
      String strCmdResult = commandResultToString(cmdResult);
      getLogWriter().info("testCreateGatewayReceiver stringResult : " + strCmdResult + ">>>>");
      assertEquals(Result.Status.OK, cmdResult.getStatus());

      TabularResultData resultData = (TabularResultData) cmdResult.getResultData();
      List<String> status = resultData.retrieveAllValues("Status");
      assertEquals(2, status.size());
      // verify there is no error in the status
      for (String stat : status) {
        assertTrue("GatewayReceiver creation failed with: " + stat, !stat.contains("ERROR:"));
      }
    } else {
      fail("testCreateGatewayReceiver failed as did not get CommandResult");
    }
    vm3.invoke(() -> verifyReceiverCreationWithAttributes(false, 10000, 11000, "localhost", 100000,
        512000, null));
    vm4.invoke(() -> verifyReceiverCreationWithAttributes(false, 10000, 11000, "localhost", 100000,
        512000, null));
  }