@SuppressWarnings("unchecked")
	public boolean equalsContent(PersonAttribute otherAttribute) {
		boolean returnValue = true;
		
		// these are the methods to compare.
		String[] methods = { "getAttributeType", "getValue", "getVoided" };
		
		Class attributeClass = this.getClass();
		
		// loop over all of the selected methods and compare this and other
		for (String methodAttribute : methods) {
			try {
				Method method = attributeClass.getMethod(methodAttribute, new Class[] {});
				
				Object thisValue = method.invoke(this);
				Object otherValue = method.invoke(otherAttribute);
				
				if (otherValue != null) {
					returnValue &= otherValue.equals(thisValue);
				}
				
			}
			catch (NoSuchMethodException e) {
				log.warn("No such method for comparison " + methodAttribute, e);
			}
			catch (IllegalAccessException | InvocationTargetException e) {
				log.error("Error while comparing attributes", e);
			}

		}
		
		return returnValue;
	}