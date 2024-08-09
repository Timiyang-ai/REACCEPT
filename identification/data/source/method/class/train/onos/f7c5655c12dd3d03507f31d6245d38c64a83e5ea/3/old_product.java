public ChannelBuffer writeToBuffer(OspfMessage ospfMessage, int interfaceState,
                                       int interfaceType) throws Exception {

        ChannelBuffer buf = null;
        switch (ospfMessage.ospfMessageType().value()) {
            case OspfParameters.HELLO:
            case OspfParameters.LSACK:
                buf = writeMessageToBuffer(ospfMessage, interfaceState);
                break;
            case OspfParameters.DD:
            case OspfParameters.LSREQUEST:
            case OspfParameters.LSUPDATE:
                buf = writeMessageToBuffer(ospfMessage, interfaceState);
                break;
            default:
                log.debug("Message Writer[Encoder] - Unknown Message to encode..!!!");
                break;
        }

        return buf;
    }