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
			
			//we do not warn about empty strings which throw number format exceptions.
			if (t.getCause() instanceof NumberFormatException && StringUtils.isBlank(getValue()))
				return null;
			
			//we do not warn for character format which throws this exception when newInstance() is called.
			if (t instanceof NoSuchMethodException)
				return null;
			
			log.warn("Unable to hydrate value: " + getValue() + " for type: " + getAttributeType(), t);
		}
		
		log.debug("Returning value: '" + getValue() + "'");
		return getValue();
	}