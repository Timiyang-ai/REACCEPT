@Override
	public void putAll(Map<? extends String, ? extends PresentationMessage> t) {
		//Map<String, PresentationMessage> compatibleMap = new HashMap<String, PresentationMessage>();
		for (Entry<? extends String, ? extends PresentationMessage> entry : t.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}