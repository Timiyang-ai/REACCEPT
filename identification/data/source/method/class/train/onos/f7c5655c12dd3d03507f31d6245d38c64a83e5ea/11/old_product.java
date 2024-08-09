public OspfMessage readFromBuffer(ChannelBuffer channelBuffer)
            throws Exception {

        if (channelBuffer.readableBytes() < OspfUtil.PACKET_MINIMUM_LENGTH) {
            log.error("Packet should have minimum length...");
            throw new OspfParseException(OspfErrorType.MESSAGE_HEADER_ERROR, OspfErrorType.BAD_MESSAGE_LENGTH);
        }

        try {
            OspfPacketHeader ospfHeader = getOspfHeader(channelBuffer);
            int len = ospfHeader.ospfPacLength() - OspfUtil.OSPF_HEADER_LENGTH;

            OspfMessage ospfMessage = null;
            switch (ospfHeader.ospfType()) {
                case OspfParameters.HELLO:
                    ospfMessage = new HelloPacket(ospfHeader);
                    break;
                case OspfParameters.DD:
                    ospfMessage = new DdPacket(ospfHeader);
                    break;
                case OspfParameters.LSREQUEST:
                    ospfMessage = new LsRequest(ospfHeader);
                    break;
                case OspfParameters.LSUPDATE:
                    ospfMessage = new LsUpdate(ospfHeader);
                    break;
                case OspfParameters.LSACK:
                    ospfMessage = new LsAcknowledge(ospfHeader);
                    break;
                default:
                    log.debug("Message Reader[Decoder] - Unknown LSA type..!!!");
                    break;
            }

            if (ospfMessage != null) {
                try {
                    log.debug("{} Received::Message Length :: {} ", ospfMessage.ospfMessageType(),
                              ospfHeader.ospfPacLength());
                    ospfMessage.readFrom(channelBuffer.readBytes(len));
                } catch (Exception e) {
                    throw new OspfParseException(OspfErrorType.OSPF_MESSAGE_ERROR,
                                                 OspfErrorType.BAD_MESSAGE);
                }

            }

            return ospfMessage;
        } catch (Exception e) {
            throw new OspfParseException(OspfErrorType.OSPF_MESSAGE_ERROR,
                                         OspfErrorType.BAD_MESSAGE);
        }
    }