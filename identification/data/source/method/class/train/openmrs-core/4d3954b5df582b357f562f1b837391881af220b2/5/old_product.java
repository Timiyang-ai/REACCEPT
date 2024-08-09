public PresentationMessage put(String key, PresentationMessage value) {
		PresentationMessage putValue = null;
		if (value.getLocale().equals(locale)) {
			putValue = internalMap.put(key, value);
		}
		return putValue;
	}