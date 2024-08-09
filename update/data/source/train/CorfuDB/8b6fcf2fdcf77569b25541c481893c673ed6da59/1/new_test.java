@Test
    public void commitReturnsAck() {
        Layout layout = TestLayoutBuilder.single(SERVERS.PORT_0);
        bootstrapServer(layout);

        long newEpoch = layout.getEpoch() + 1;
        Layout newLayout = TestLayoutBuilder.single(SERVERS.PORT_0);
        newLayout.setEpoch(newEpoch);

        // set epoch on servers
        setEpoch(newEpoch);

        sendPrepare(newEpoch, HIGH_RANK);
        Assertions.assertThat(getLastMessage().getMsgType()).isEqualTo(CorfuMsgType.LAYOUT_PREPARE_ACK);

        sendPropose(newEpoch, HIGH_RANK, newLayout);
        Assertions.assertThat(getLastMessage().getMsgType()).isEqualTo(CorfuMsgType.ACK);

        sendCommitted(newEpoch, newLayout);
        Assertions.assertThat(getLastMessage().getMsgType()).isEqualTo(CorfuMsgType.ACK);
    }