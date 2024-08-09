public PresentationMessage getPresentation(String key, Locale forLocale) {
		PresentationMessage foundPM = null;
		PresentationMessageMap codeMessageMap = localizedMap.get(forLocale);
		if ((codeMessageMap != null) && codeMessageMap.containsKey(key)) {
			foundPM = codeMessageMap.get(key);
		}
		return foundPM;
	}