public Message<Object> toMessage(SocketReader socketReader) throws Exception {
		return fromRaw(socketReader);
	}