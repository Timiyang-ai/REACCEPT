public byte[] getMessage(OspfMessage ospfMessage, int interfaceIndex, int interfaceState) {

        byte[] buf = null;
        switch (ospfMessage.ospfMessageType().value()) {
            case OspfParameters.HELLO:
            case OspfParameters.LSACK:
            case OspfParameters.DD:
            case OspfParameters.LSREQUEST:
            case OspfParameters.LSUPDATE:
                buf = writeMessageToBytes(ospfMessage, interfaceIndex, interfaceState);
                break;
            default:
                log.debug("Message Writer[Encoder] - Unknown Message to encode..!!!");
                break;
        }

        return buf;
    }