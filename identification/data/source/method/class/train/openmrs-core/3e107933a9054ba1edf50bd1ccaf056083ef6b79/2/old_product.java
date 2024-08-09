@SuppressWarnings("unchecked")
	public boolean equalsContent(PersonAddress otherAddress) {
		boolean returnValue = true;
		
		// these are the methods to compare. All are expected to be Strings
		String[] methods = { "getAddress1", "getAddress2", "getAddress3", "getAddress4", "getAddress5", "getCityVillage",
		        "getNeighborhoodCell", "getCountyDistrict", "getStateProvince", "getCountry", "getPostalCode",
		        "getLatitude", "getLongitude" };
		
		Class addressClass = this.getClass();
		
		// loop over all of the selected methods and compare this and other
		for (String methodName : methods) {
			try {
				Method method = addressClass.getMethod(methodName, new Class[] {});
				
				String thisValue = (String) method.invoke(this);
				String otherValue = (String) method.invoke(otherAddress);
				
				if (otherValue != null && otherValue.length() > 0)
					returnValue &= otherValue.equals(thisValue);
				
			}
			catch (NoSuchMethodException e) {
				log.warn("No such method for comparison " + methodName, e);
			}
			catch (IllegalAccessException e) {
				log.error("Error while comparing addresses", e);
			}
			catch (InvocationTargetException e) {
				log.error("Error while comparing addresses", e);
			}
			
		}
		
		return returnValue;
	}