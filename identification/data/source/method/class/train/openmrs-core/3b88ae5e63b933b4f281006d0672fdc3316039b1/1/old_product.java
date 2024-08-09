public void setSerializers(List<? extends OpenmrsSerializer> serializers) {
		serializerMap = new LinkedHashMap<Class<? extends OpenmrsSerializer>, OpenmrsSerializer>();
		if (serializers != null) {
			for (OpenmrsSerializer s : serializers) {
				serializerMap.put(s.getClass(), s);
			}
		}
	}