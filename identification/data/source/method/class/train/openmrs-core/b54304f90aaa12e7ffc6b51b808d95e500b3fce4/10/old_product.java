@Override
	public Date deserialize(String serializedValue) {
		if (StringUtils.isBlank(serializedValue))
			return null;
		try {
			return new SimpleDateFormat(dateFormat).parse(serializedValue);
		}
		catch (Exception ex) {
			throw new InvalidCustomValueException("Invalid date: " + serializedValue);
		}
	}