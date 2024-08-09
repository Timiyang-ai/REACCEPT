public void setSerializers(List<? extends OpenmrsSerializer> serializers) {
		if (serializers == null || serializerMap == null) {
			serializerMap = new LinkedHashMap<Class<? extends OpenmrsSerializer>, OpenmrsSerializer>();
		}
		if (serializers != null) {
			for (OpenmrsSerializer s : serializers) {
				serializerMap.put(s.getClass(), s);
			}
		}
	}