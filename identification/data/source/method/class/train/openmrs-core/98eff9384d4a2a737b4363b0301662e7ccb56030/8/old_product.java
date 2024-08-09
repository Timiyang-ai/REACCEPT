@SuppressWarnings("unchecked")
	public Object getHydratedObject() {
		try {
			Class c = OpenmrsClassLoader.getInstance().loadClass(getAttributeType().getFormat());
			Object o = c.newInstance();
			if (o instanceof Attributable) {
				Attributable attr = (Attributable) o;
				return attr.hydrate(getValue());
			}
		}
		catch (Throwable t) {
			log.warn("Unable to hydrate value: " + getValue() + " for type: " + getAttributeType(), t);
		}
		
		log.debug("Returning value: '" + getValue() + "'");
		return getValue();
	}