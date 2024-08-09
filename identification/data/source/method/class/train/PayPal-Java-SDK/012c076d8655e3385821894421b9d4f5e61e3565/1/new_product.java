public Map<String, String> getValuesByCategory(String category) {
		String key;
		HashMap<String, String> map = new HashMap<String, String>();
		for (Object obj : properties.keySet()) {
			key = (String) obj;
			if (key.contains(category)) {
				map.put(key, properties.getProperty(key));
			}
		}
		return map;
	}