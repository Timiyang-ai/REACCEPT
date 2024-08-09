public OspfMessage readFromBuffer(ChannelBuffer channelBuffer)
            throws Exception {

        try {
            OspfPacketHeader ospfHeader = getOspfHeader(channelBuffer);
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
                    ospfMessage.readFrom(channelBuffer.readBytes(ospfHeader.ospfPacLength() -
                                                                         OspfUtil.OSPF_HEADER_LENGTH));
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