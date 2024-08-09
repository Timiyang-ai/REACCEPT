@SuppressWarnings("unchecked")
    public Object getHydratedObject() {
		try {
			Class c = OpenmrsClassLoader.getInstance().loadClass(getAttributeType().getFormat());
			Object o = c.newInstance();
			if (o instanceof Attributable) {
				Attributable attr = (Attributable)o;
				return attr.hydrate(getValue());
			}
		}
		catch (Throwable t) {
			// pass
		}
		
		log.debug("Returning value: '" + getValue() + "'");
		return getValue();
	}