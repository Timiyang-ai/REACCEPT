public Object fromMessage(Message<?> message) throws Exception {
		if (this.messageFormat < MessageFormats.FORMAT_IMPLICIT) {
			return getPayloadAsBytes(message);
		}
		return message.getPayload();
	}