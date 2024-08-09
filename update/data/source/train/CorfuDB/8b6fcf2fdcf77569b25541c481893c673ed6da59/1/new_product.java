@SuppressWarnings("checkstyle:magicnumber")
    private void commitReturnsAck(LayoutServer s1, Integer reboot, long baseEpoch) {

        if ((reboot & 1) > 0) {
            s1.reboot();
        }
        long newEpoch = baseEpoch + reboot;
        sendMessage(new CorfuPayloadMsg<>(CorfuMsgType.SET_EPOCH, newEpoch));

        Layout layout = TestLayoutBuilder.single(SERVERS.PORT_0);
        layout.setEpoch(newEpoch);

        sendPrepare(newEpoch, HIGH_RANK);
        Assertions.assertThat(getLastMessage().getMsgType()).isEqualTo(CorfuMsgType.LAYOUT_PREPARE_ACK);

        if ((reboot & 2) > 0) {
            log.debug("Rebooted server because reboot & 2 {}", reboot & 2);
            s1.reboot();
        }

        sendPropose(newEpoch, HIGH_RANK, layout);
        Assertions.assertThat(getLastMessage().getMsgType()).isEqualTo(CorfuMsgType.ACK);

        if ((reboot & 4) > 0) {
            s1.reboot();
        }

        sendCommitted(newEpoch, layout);
        Assertions.assertThat(getLastMessage().getMsgType()).isEqualTo(CorfuMsgType.ACK);

        if ((reboot & 8) > 0) {
            s1.reboot();
        }

        sendCommitted(newEpoch, layout);
        Assertions.assertThat(getLastMessage().getMsgType()).isEqualTo(CorfuMsgType.ACK);

        requestLayout(newEpoch);
        Assertions.assertThat(getLastMessage().getMsgType()).isEqualTo(CorfuMsgType.LAYOUT_RESPONSE);
        Assertions.assertThat(((LayoutMsg) getLastMessage()).getLayout()).isEqualTo(layout);

    }