public Message<byte[]> toMessage(SocketReader socketReader) throws Exception {
		return fromRaw(socketReader);
	}