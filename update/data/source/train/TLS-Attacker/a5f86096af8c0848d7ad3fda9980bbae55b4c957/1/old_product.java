@Override
    public int parseMessageAction(byte[] message, int pointer) {
        if (message[pointer] != HandshakeMessageType.SERVER_HELLO.getValue()) {
            throw new InvalidMessageTypeException("This is not a server hello message");
        }
        protocolMessage.setType(message[pointer]);

        int currentPointer = pointer + HandshakeByteLength.MESSAGE_TYPE;
        int nextPointer = currentPointer + HandshakeByteLength.MESSAGE_TYPE_LENGTH;
        int length = ArrayConverter.bytesToInt(Arrays.copyOfRange(message, currentPointer, nextPointer));
        protocolMessage.setLength(length);

        currentPointer = nextPointer;
        nextPointer = currentPointer + RecordByteLength.PROTOCOL_VERSION;
        ProtocolVersion serverProtocolVersion = ProtocolVersion.getProtocolVersion(Arrays.copyOfRange(message,
                currentPointer, nextPointer));

        protocolMessage.setProtocolVersion(Arrays.copyOfRange(message, currentPointer, nextPointer));
        if (serverProtocolVersion == null) {
            System.out.println("Unknown protocolversion"
                    + ArrayConverter.bytesToHexString(Arrays.copyOfRange(message, currentPointer, nextPointer)));// TODO
        }

        currentPointer = nextPointer;
        nextPointer = currentPointer + HandshakeByteLength.UNIX_TIME;
        byte[] serverUnixTime = Arrays.copyOfRange(message, currentPointer, nextPointer);
        protocolMessage.setUnixTime(serverUnixTime);
        // System.out.println(new
        // Date(ArrayConverter.bytesToLong(serverUnixTime) * 1000));

        currentPointer = nextPointer;
        nextPointer = currentPointer + HandshakeByteLength.RANDOM;
        byte[] serverRandom = Arrays.copyOfRange(message, currentPointer, nextPointer);
        protocolMessage.setRandom(serverRandom);

        tlsContext.setServerRandom(ArrayConverter.concatenate(protocolMessage.getUnixTime().getValue(), protocolMessage
                .getRandom().getValue()));

        currentPointer = nextPointer;
        int sessionIdLength = message[currentPointer] & 0xFF;
        currentPointer = currentPointer + HandshakeByteLength.SESSION_ID_LENGTH;
        nextPointer = currentPointer + sessionIdLength;
        byte[] sessionId = Arrays.copyOfRange(message, currentPointer, nextPointer);
        protocolMessage.setSessionId(sessionId);

        // handle unknown SessionID during Session resumption
        if (tlsContext.getConfig().isSessionResumption()
                && !(Arrays.equals(tlsContext.getSessionID(), protocolMessage.getSessionId().getValue()))) {
            throw new WorkflowExecutionException("Session ID is unknown to the Client");
        }

        tlsContext.setSessionID(sessionId);

        currentPointer = nextPointer;
        nextPointer = currentPointer + HandshakeByteLength.CIPHER_SUITE;
        CipherSuite selectedCipher;
        try {
            selectedCipher = CipherSuite.getCipherSuite(Arrays.copyOfRange(message, currentPointer, nextPointer));
        } catch (UnknownCiphersuiteException ex) {
            LOGGER.error(ex.getLocalizedMessage());
            LOGGER.debug(ex.getLocalizedMessage(), ex);
            selectedCipher = CipherSuite.TLS_UNKNOWN_CIPHER;
        }
        // System.out.println(selectedCipher);
        protocolMessage.setSelectedCipherSuite(selectedCipher.getByteValue());

        tlsContext.setSelectedCipherSuite(CipherSuite.getCipherSuite(protocolMessage.getSelectedCipherSuite()
                .getValue()));
        tlsContext.setSelectedProtocolVersion(serverProtocolVersion);
        tlsContext.initiliazeTlsMessageDigest();

        currentPointer = nextPointer;
        byte compression = message[currentPointer];
        currentPointer += HandshakeByteLength.COMPRESSION;
        protocolMessage.setSelectedCompressionMethod(compression);

        tlsContext.setCompressionMethod(CompressionMethod.getCompressionMethod(protocolMessage
                .getSelectedCompressionMethod().getValue()));

        if ((currentPointer - pointer) < length) {
            currentPointer += ExtensionByteLength.EXTENSIONS;
            while ((currentPointer - pointer) < length) {
                nextPointer = currentPointer + ExtensionByteLength.TYPE;
                byte[] extensionType = Arrays.copyOfRange(message, currentPointer, nextPointer);
                // Not implemented/unknown extensions will generate an Exception
                // ...
                try {
                    ExtensionHandler<? extends ExtensionMessage> eh = ExtensionType.getExtensionType(extensionType)
                            .getExtensionHandler();
                    currentPointer = eh.parseExtension(message, currentPointer);
                    protocolMessage.addExtension(eh.getExtensionMessage());
                } // ... which we catch, then disregard that extension and carry
                  // on.
                catch (Exception ex) {
                    currentPointer = nextPointer;
                    nextPointer += 2;
                    currentPointer += ArrayConverter.bytesToInt(Arrays
                            .copyOfRange(message, currentPointer, nextPointer));
                    nextPointer += 2;
                    currentPointer += 2;
                }
            }
        }

        protocolMessage.setCompleteResultingMessage(Arrays.copyOfRange(message, pointer, currentPointer));

        return currentPointer;
    }