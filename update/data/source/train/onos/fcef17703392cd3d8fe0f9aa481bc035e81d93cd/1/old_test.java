@Test
    public void testAllocateLabel() {
       List<LspLocalLabelInfo> lspLocalLabelInfoList;
       Iterator<LspLocalLabelInfo> iterator;
       LspLocalLabelInfo lspLocalLabelInfo;
       DeviceId deviceId;
       LabelResourceId inLabelId;
       LabelResourceId outLabelId;
       PortNumber inPort;
       PortNumber outPort;

       // check allocation result
       assertThat(pceccHandler.allocateLabel(tunnel), is(true));

       // Check list of devices with IN and OUT labels whether stored properly in store
       pceccTunnelInfo = pceStore.getTunnelInfo(tunnel.tunnelId());
       lspLocalLabelInfoList = pceccTunnelInfo.lspLocalLabelInfoList();
       iterator = lspLocalLabelInfoList.iterator();

       // Retrieve values and check device5
       lspLocalLabelInfo = (DefaultLspLocalLabelInfo) iterator.next();
       deviceId = lspLocalLabelInfo.deviceId();
       inLabelId = lspLocalLabelInfo.inLabelId();
       outLabelId = lspLocalLabelInfo.outLabelId();
       inPort = lspLocalLabelInfo.inPort();
       outPort = lspLocalLabelInfo.outPort();

       assertThat(deviceId, is(deviceId5));
       assertThat(inLabelId, is(notNullValue()));
       assertThat(outLabelId, is(nullValue()));
       assertThat(inPort, is(port5));
       assertThat(outPort, is(nullValue()));

       // Next element check
       // Retrieve values and check device4
       lspLocalLabelInfo = (DefaultLspLocalLabelInfo) iterator.next();
       deviceId = lspLocalLabelInfo.deviceId();
       inLabelId = lspLocalLabelInfo.inLabelId();
       outLabelId = lspLocalLabelInfo.outLabelId();
       inPort = lspLocalLabelInfo.inPort();
       outPort = lspLocalLabelInfo.outPort();

       assertThat(deviceId, is(deviceId4));
       assertThat(inLabelId, is(notNullValue()));
       assertThat(outLabelId, is(notNullValue()));
       assertThat(inPort, is(port4));
       assertThat(outPort, is(port5));

       // Next element check
       // Retrieve values and check device3
       lspLocalLabelInfo = (DefaultLspLocalLabelInfo) iterator.next();
       deviceId = lspLocalLabelInfo.deviceId();
       inLabelId = lspLocalLabelInfo.inLabelId();
       outLabelId = lspLocalLabelInfo.outLabelId();
       inPort = lspLocalLabelInfo.inPort();
       outPort = lspLocalLabelInfo.outPort();

       assertThat(deviceId, is(deviceId3));
       assertThat(inLabelId, is(notNullValue()));
       assertThat(outLabelId, is(notNullValue()));
       assertThat(inPort, is(port3));
       assertThat(outPort, is(port4));

       // Next element check
       // Retrieve values and check device2
       lspLocalLabelInfo = (DefaultLspLocalLabelInfo) iterator.next();
       deviceId = lspLocalLabelInfo.deviceId();
       inLabelId = lspLocalLabelInfo.inLabelId();
       outLabelId = lspLocalLabelInfo.outLabelId();
       inPort = lspLocalLabelInfo.inPort();
       outPort = lspLocalLabelInfo.outPort();

       assertThat(deviceId, is(deviceId2));
       assertThat(inLabelId, is(notNullValue()));
       assertThat(outLabelId, is(notNullValue()));
       assertThat(inPort, is(port2));
       assertThat(outPort, is(port3));

       // Next element check
       // Retrieve values and check device1
       lspLocalLabelInfo = (DefaultLspLocalLabelInfo) iterator.next();
       deviceId = lspLocalLabelInfo.deviceId();
       inLabelId = lspLocalLabelInfo.inLabelId();
       outLabelId = lspLocalLabelInfo.outLabelId();
       inPort = lspLocalLabelInfo.inPort();
       outPort = lspLocalLabelInfo.outPort();

       assertThat(deviceId, is(deviceId1));
       assertThat(inLabelId, is(nullValue()));
       assertThat(outLabelId, is(notNullValue()));
       assertThat(inPort, is(nullValue()));
       assertThat(outPort, is(port2));
    }