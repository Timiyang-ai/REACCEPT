public String serialize(Object o) throws SerializationException {
		aliasClassName(o.getClass());
		return xstream.toXML(o);
	}