public void processOSPFMessage(OspfMessage ospfMessage, ChannelHandlerContext ctx) throws Exception {
        log.debug("OspfChannelHandler::processOSPFMessage...!!!");

        if (!validateMessage(ospfMessage)) {
            return;
        }

        switch (ospfMessage.ospfMessageType().value()) {
            case OspfParameters.HELLO:
                processHelloMessage(ospfMessage, ctx);
                break;
            case OspfParameters.DD:
                processDdMessage(ospfMessage, ctx);
                break;
            case OspfParameters.LSREQUEST:
                processLsRequestMessage(ospfMessage, ctx);
                break;
            case OspfParameters.LSUPDATE:
                processLsUpdateMessage(ospfMessage, ctx);
                break;
            case OspfParameters.LSACK:
                processLsAckMessage(ospfMessage, ctx);
                break;
            default:
                log.debug("Unknown packet to process...!!!");
                break;
        }
    }