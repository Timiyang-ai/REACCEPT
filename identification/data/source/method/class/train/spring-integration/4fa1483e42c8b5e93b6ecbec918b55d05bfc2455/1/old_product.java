public byte[] fromMessage(Message<?> message) throws Exception {
		return getPayloadAsBytes(message);
	}