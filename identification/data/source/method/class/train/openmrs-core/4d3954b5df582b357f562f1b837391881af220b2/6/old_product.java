public String serialize(T typedValue) {
		if (typedValue == null) {
			return null;
		}
		return typedValue.getUuid();
	}