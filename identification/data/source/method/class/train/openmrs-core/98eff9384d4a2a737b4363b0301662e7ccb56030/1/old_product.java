@SuppressWarnings("unchecked")
	public Object getHydratedObject() {
		
		if (getValue() == null)
			return null;
		
		try {
			Class c = OpenmrsClassLoader.getInstance().loadClass(getAttributeType().getFormat());
			try {
				Object o = c.newInstance();
				if (o instanceof Attributable) {
					Attributable attr = (Attributable) o;
					return attr.hydrate(getValue());
				}
			}
			catch (InstantiationException e) {
				// try to hydrate the object with the String constructor
				log.trace("Unable to call no-arg constructor for class: " + c.getName());
				Object o = c.getConstructor(String.class).newInstance(getValue());
				return o;
			}
		}
		catch (Throwable t) {
			//Do not log warnings for legal but empty values.
			if (!((t.getCause() instanceof NumberFormatException && StringUtils.isBlank(getValue())) || t instanceof NoSuchMethodException)) {
				log.warn("Unable to hydrate value: " + getValue() + " for type: " + getAttributeType(), t);
			}
		}
		
		log.debug("Returning value: '" + getValue() + "'");
		return getValue();
	}