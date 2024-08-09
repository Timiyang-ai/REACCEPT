public void readFrom(ChannelBuffer channelBuffer) {
        while (channelBuffer.readableBytes() >= IsisUtil.FOUR_BYTES) {
            byte[] tempByteArray = new byte[IsisUtil.FOUR_BYTES];
            channelBuffer.readBytes(tempByteArray, 0, IsisUtil.FOUR_BYTES);
            this.setIpAddress(Ip4Address.valueOf(tempByteArray));

        }
    }