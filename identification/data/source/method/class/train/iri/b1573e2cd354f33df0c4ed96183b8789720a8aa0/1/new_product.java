public static ProtocolHeader parseHeader(ByteBuffer buf)
            throws UnknownMessageTypeException, InvalidProtocolMessageLengthException {

        // extract type of message
        byte type = buf.get();
        ProtocolMessage protoMsg = ProtocolMessage.fromTypeID(type);
        if (protoMsg == null) {
            throw new UnknownMessageTypeException(String.format("got unknown message type in protocol: %d", type));
        }

        // extract length of message
        short advertisedMsgLength = buf.getShort();
        if ((advertisedMsgLength > protoMsg.getMaxLength())
                || (!protoMsg.supportsDynamicLength() && advertisedMsgLength < protoMsg.getMaxLength())) {
            throw new InvalidProtocolMessageLengthException(String.format(
                    "advertised length: %d bytes; max length: %d bytes", advertisedMsgLength, protoMsg.getMaxLength()));
        }

        return new ProtocolHeader(protoMsg, advertisedMsgLength);
    }