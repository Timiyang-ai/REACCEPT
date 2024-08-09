static
	public Map<String, ?> decode(Map<FieldName, ?> map){
		Map<String, Object> result = Maps.newLinkedHashMap();

		Collection<? extends Map.Entry<FieldName, ?>> entries = map.entrySet();
		for(Map.Entry<FieldName, ?> entry : entries){
			FieldName key = entry.getKey();
			Object value = entry.getValue();

			if(key == null){
				continue;
			}

			result.put(key.getValue(), decode(value));
		}

		return result;
	}