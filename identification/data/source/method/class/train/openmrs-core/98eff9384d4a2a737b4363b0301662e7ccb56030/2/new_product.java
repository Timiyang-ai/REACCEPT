@SuppressWarnings("unchecked")
	public Object getHydratedObject() {
		
		if (getValue() == null) {
			return null;
		}
		
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
				return c.getConstructor(String.class).newInstance(getValue());
			}
		}
		catch (Exception e) {
			
			// No need to warn if the input was blank
			if (StringUtils.isBlank(getValue())) {
				return null;
			}
			
			log.warn("Unable to hydrate value: " + getValue() + " for type: " + getAttributeType(), e);
		}
		
		log.debug("Returning value: '" + getValue() + "'");
		return getValue();
	}