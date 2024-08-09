public void setSerializers(List<? extends OpenmrsSerializer> serializers) {
		if (serializers == null || serializerMap == null) {
			setSerializerMap(new LinkedHashMap<>());
		}
		if (serializers != null) {
			for (OpenmrsSerializer s : serializers) {
				serializerMap.put(s.getClass(), s);
			}
		}
	}