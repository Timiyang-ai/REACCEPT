static Object parsePropertyValue(String propertyValue) {
		if (propertyValue == null) {
			return null;
		}
		// read boolean value
		final Map<String, Boolean> booleanConversionMap = new HashMap<>();
		booleanConversionMap.put("0", false);
		booleanConversionMap.put("1", true);
		booleanConversionMap.put("false", false);
		booleanConversionMap.put("true", true);
		booleanConversionMap.put("no", false);
		booleanConversionMap.put("yes", true);
		if (booleanConversionMap.containsKey(propertyValue)) {
			return booleanConversionMap.get(propertyValue.toLowerCase());
		}
		// read number value
		try {
			return Integer.valueOf(propertyValue);
		} catch (NumberFormatException nfe) {
			// ok, so not a number
		}
		// read TransportStrategy value
		try {
			return TransportStrategy.valueOf(propertyValue);
		} catch (IllegalArgumentException nfe) {
			// ok, so not a TransportStrategy either
		}
		// return value as is (which should be string)
		return propertyValue;
	}