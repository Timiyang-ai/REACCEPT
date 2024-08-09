static
	public Map<String, ?> decode(Map<FieldName, ?> map){
		Map<String, Object> result = new LinkedHashMap<>();

		Collection<? extends Map.Entry<FieldName, ?>> entries = map.entrySet();
		for(Map.Entry<FieldName, ?> entry : entries){
			FieldName key = entry.getKey();
			Object value = entry.getValue();

			try {
				result.put(key != null ? key.getValue() : null, decode(value));
			} catch(EvaluationException ee){
				// Ignored
			}
		}

		return result;
	}