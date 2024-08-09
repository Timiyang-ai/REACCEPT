@Override
    public List<ProtocolMessage> sendMessages(List<ProtocolMessage> messages) {
        MessageBytesCollector messageBytesCollector = new MessageBytesCollector();
        for (ProtocolMessage message : messages) {
            LOGGER.debug("Preparing " + message.toCompactString());
            byte[] protocolMessageBytes = prepareProtocolMessageBytes(message);
            if (message.isGoingToBeSent()) {
                messageBytesCollector.appendProtocolMessageBytes(protocolMessageBytes);
            }
            if (message.getRecords() != null && !message.getRecords().isEmpty()) {
                byte[] recordBytes = prepareRecords(message, messageBytesCollector);
                messageBytesCollector.appendRecordBytes(recordBytes);
                messageBytesCollector.flushProtocolMessageBytes();
            }
        }
        try {
            LOGGER.debug("Sending " + ArrayConverter.bytesToHexString(messageBytesCollector.getRecordBytes()));
            sendData(context.getTransportHandler(), messageBytesCollector);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return messages;
    }