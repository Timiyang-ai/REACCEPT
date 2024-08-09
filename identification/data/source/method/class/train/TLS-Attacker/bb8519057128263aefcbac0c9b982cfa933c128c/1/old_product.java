@Override
    public MessageActionResult sendMessages(List<ProtocolMessage> messages, List<Record> records) {
        if (!proceed) {
            return new MessageActionResult(new LinkedList<Record>(), new LinkedList<ProtocolMessage>());
        }
        if (records == null) {
            records = new LinkedList<>();
        }
        int recordPosition = 0;
        ProtocolMessageType lastType = null;
        MessageBytesCollector messageBytesCollector = new MessageBytesCollector();
        for (ProtocolMessage message : messages) {
            if (message.getProtocolMessageType() != lastType && lastType != null
                    && context.getConfig().isFlushOnMessageTypeChange()) {
                recordPosition = flushBytesToRecords(messageBytesCollector, lastType, records, recordPosition);
                if (lastType == ProtocolMessageType.CHANGE_CIPHER_SPEC) {
                    context.getRecordLayer().updateEncryptionCipher();
                }
            }
            lastType = message.getProtocolMessageType();
            LOGGER.debug("Preparing " + message.toCompactString());
            byte[] protocolMessageBytes = prepareProtocolMessageBytes(message);
            if (message.isGoingToBeSent()) {
                messageBytesCollector.appendProtocolMessageBytes(protocolMessageBytes);
            }
            if (context.getConfig().isCreateIndividualRecords()) {
                recordPosition = flushBytesToRecords(messageBytesCollector, lastType, records, recordPosition);
            }
        }
        flushBytesToRecords(messageBytesCollector, lastType, records, recordPosition);
        // Save Bytes and parse them afterwards
        byte[] toSendBytes = messageBytesCollector.getRecordBytes();
        try {
            sendData(messageBytesCollector);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // TODO Parse our messages
        return new MessageActionResult(records, messages);
    }