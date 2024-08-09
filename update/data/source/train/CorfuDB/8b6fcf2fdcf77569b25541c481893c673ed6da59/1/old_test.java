@Test
    public void commitReturnsAck() {
        Layout layout = TestLayoutBuilder.single(9000);
        bootstrapServer(layout);

        long newEpoch = layout.getEpoch() + 1;
        Layout newLayout = TestLayoutBuilder.single(9000);
        newLayout.setEpoch(newEpoch);

        // set epoch on servers
        setEpoch(newEpoch);

        sendPrepare(newEpoch, 100);
        Assertions.assertThat(getLastMessage().getMsgType()).isEqualTo(CorfuMsgType.LAYOUT_PREPARE_ACK);

        sendPropose(newEpoch, 100, newLayout);
        Assertions.assertThat(getLastMessage().getMsgType()).isEqualTo(CorfuMsgType.ACK);

        sendCommitted(newEpoch, newLayout);
        Assertions.assertThat(getLastMessage().getMsgType()).isEqualTo(CorfuMsgType.ACK);
    }