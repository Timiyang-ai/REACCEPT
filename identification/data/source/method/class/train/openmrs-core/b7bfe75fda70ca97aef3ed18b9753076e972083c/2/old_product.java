public String serialize(Object o) throws SerializationException {
		
		return xstream.toXML(o);
	}