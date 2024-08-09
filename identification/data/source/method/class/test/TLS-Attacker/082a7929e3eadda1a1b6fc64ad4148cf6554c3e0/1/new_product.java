@Override
    public int parseMessageAction(byte[] message, int pointer) {
	if (message[pointer] != HandshakeMessageType.SERVER_KEY_EXCHANGE.getValue()) {
	    throw new InvalidMessageTypeException(HandshakeMessageType.SERVER_KEY_EXCHANGE);
	}
	HandshakeMessageFields protocolMessageFields = (HandshakeMessageFields) protocolMessage.getMessageFields();

	protocolMessage.setType(message[pointer]);

	int currentPointer = pointer + HandshakeByteLength.MESSAGE_TYPE;
	int nextPointer = currentPointer + HandshakeByteLength.MESSAGE_TYPE_LENGTH;
	int length = ArrayConverter.bytesToInt(Arrays.copyOfRange(message, currentPointer, nextPointer));
	protocolMessageFields.setLength(length);

	currentPointer = nextPointer;
	nextPointer = currentPointer + HandshakeByteLength.DH_PARAM_LENGTH;
	int pLength = ArrayConverter.bytesToInt(Arrays.copyOfRange(message, currentPointer, nextPointer));
	protocolMessage.setpLength(pLength);

	currentPointer = nextPointer;
	nextPointer = currentPointer + protocolMessage.getpLength().getValue();
	BigInteger p = new BigInteger(1, Arrays.copyOfRange(message, currentPointer, nextPointer));
	protocolMessage.setP(p);

	currentPointer = nextPointer;
	nextPointer = currentPointer + HandshakeByteLength.DH_PARAM_LENGTH;
	int gLength = ArrayConverter.bytesToInt(Arrays.copyOfRange(message, currentPointer, nextPointer));
	protocolMessage.setgLength(gLength);

	currentPointer = nextPointer;
	nextPointer = currentPointer + protocolMessage.getgLength().getValue();
	BigInteger g = new BigInteger(1, Arrays.copyOfRange(message, currentPointer, nextPointer));
	protocolMessage.setG(g);

	currentPointer = nextPointer;
	nextPointer = currentPointer + HandshakeByteLength.DH_PARAM_LENGTH;
	int publicKeyLength = ArrayConverter.bytesToInt(Arrays.copyOfRange(message, currentPointer, nextPointer));
	protocolMessage.setPublicKeyLength(publicKeyLength);

	currentPointer = nextPointer;
	nextPointer = currentPointer + protocolMessage.getPublicKeyLength().getValue();
	BigInteger publicKey = new BigInteger(1, Arrays.copyOfRange(message, currentPointer, nextPointer));
	protocolMessage.setPublicKey(publicKey);

	byte[] dhParams = ArrayConverter
		.concatenate(ArrayConverter.intToBytes(protocolMessage.getpLength().getValue(),
			HandshakeByteLength.DH_PARAM_LENGTH), BigIntegers.asUnsignedByteArray(protocolMessage.getP()
			.getValue()), ArrayConverter.intToBytes(protocolMessage.getgLength().getValue(),
			HandshakeByteLength.DH_PARAM_LENGTH), BigIntegers.asUnsignedByteArray(protocolMessage.getG()
			.getValue()), ArrayConverter.intToBytes(protocolMessage.getPublicKeyLength().getValue(),
			HandshakeByteLength.DH_PARAM_LENGTH), BigIntegers.asUnsignedByteArray(protocolMessage
			.getPublicKey().getValue()));
	InputStream is = new ByteArrayInputStream(dhParams);

	try {
	    ServerDHParams publicKeyParameters = ServerDHParams.parse(is);

	    tlsContext.setServerDHParameters(publicKeyParameters);

	    currentPointer = nextPointer;
	    nextPointer++;
	    HashAlgorithm ha = HashAlgorithm.getHashAlgorithm(message[currentPointer]);
	    protocolMessage.setHashAlgorithm(ha.getValue());

	    currentPointer = nextPointer;
	    nextPointer++;
	    SignatureAlgorithm sa = SignatureAlgorithm.getSignatureAlgorithm(message[currentPointer]);
	    protocolMessage.setSignatureAlgorithm(sa.getValue());

	    currentPointer = nextPointer;
	    nextPointer = currentPointer + HandshakeByteLength.SIGNATURE_LENGTH;
	    int signatureLength = ArrayConverter.bytesToInt(Arrays.copyOfRange(message, currentPointer, nextPointer));
	    protocolMessage.setSignatureLength(signatureLength);

	    currentPointer = nextPointer;
	    nextPointer = currentPointer + signatureLength;
	    protocolMessage.setSignature(Arrays.copyOfRange(message, currentPointer, nextPointer));

	    protocolMessage.setCompleteResultingMessage(Arrays.copyOfRange(message, pointer, nextPointer));

	    return nextPointer;
	} catch (IOException ex) {
	    throw new WorkflowExecutionException("DH public key parsing failed", ex);
	}
    }