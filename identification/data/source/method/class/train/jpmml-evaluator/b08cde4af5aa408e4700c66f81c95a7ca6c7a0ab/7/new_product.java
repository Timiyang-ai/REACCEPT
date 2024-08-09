static
	public Map<String, ?> decode(Map<FieldName, ?> map){
		Map<String, Object> result = new LinkedHashMap<>();

		Collection<? extends Map.Entry<FieldName, ?>> entries = map.entrySet();
		for(Map.Entry<FieldName, ?> entry : entries){
			FieldName name = entry.getKey();
			Object value = entry.getValue();

			try {
				result.put(name != null ? name.getValue() : null, decode(value));
			} catch(UnsupportedOperationException uoe){
				// Ignored
			}
		}

		return result;
	}