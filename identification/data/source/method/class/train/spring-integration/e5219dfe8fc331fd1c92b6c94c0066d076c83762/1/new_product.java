public Message<?> toMessage(Object object) {
		if (object == null) {
			return null;
		}
		if (object instanceof Message<?>) {
			return (Message<?>) object;
		}
		return MessageBuilder.withPayload(object).build();
	}