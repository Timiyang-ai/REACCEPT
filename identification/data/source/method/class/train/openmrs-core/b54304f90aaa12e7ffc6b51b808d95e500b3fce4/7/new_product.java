public static void checkRequiredVersion(String version, String value) throws ModuleException {
		// need to externalize this string
		String separator = "-";
		
		if (value != null && !value.equals("")) {
			if ((value.indexOf("*") > 0 || value.indexOf(separator) > 0) && (!isVersionWithQualifier(value))) {
				// if it contains "*" or "-" then we must separate those two
				// assume it's always going to be two part
				// assign the upper and lower bound
				// if there's no "-" to split lower and upper bound
				// then assign the same value for the lower and upper
				String lowerBound = value;
				String upperBound = value;
				
				int indexOfSeparator = value.indexOf(separator);
				while (indexOfSeparator > 0) {
					lowerBound = value.substring(0, indexOfSeparator);
					upperBound = value.substring(indexOfSeparator + 1);
					if (upperBound.matches("^\\s?\\d+.*")) {
						break;
					}
					indexOfSeparator = value.indexOf(separator, indexOfSeparator + 1);
				}
				
				// only preserve part of the string that match the following format:
				// - xx.yy.*
				// - xx.yy.zz*
				lowerBound = StringUtils.remove(lowerBound, lowerBound.replaceAll("^\\s?\\d+[\\.\\d+\\*?|\\.\\*]+", ""));
				upperBound = StringUtils.remove(upperBound, upperBound.replaceAll("^\\s?\\d+[\\.\\d+\\*?|\\.\\*]+", ""));
				
				// if the lower contains "*" then change it to zero
				if (lowerBound.indexOf("*") > 0) {
					lowerBound = lowerBound.replaceAll("\\*", "0");
				}
				
				// if the upper contains "*" then change it to 999
				// assuming 999 will be the max revision number for openmrs
				if (upperBound.indexOf("*") > 0) {
					upperBound = upperBound.replaceAll("\\*", "999");
				}
				
				int lowerReturn = compareVersion(version, lowerBound);
				
				int upperReturn = compareVersion(version, upperBound);
				
				if (lowerReturn < 0 || upperReturn > 0) {
					String ms = Context.getMessageSourceService().getMessage("Module.requireVersion.outOfBounds",
					    new String[] { lowerBound, upperBound, version }, Context.getLocale());
					throw new ModuleException(ms);
				}
			} else {
				if (compareVersion(version, value) < 0) {
					String ms = Context.getMessageSourceService().getMessage("Module.requireVersion.belowLowerBound",
					    new String[] { value, version }, Context.getLocale());
					throw new ModuleException(ms);
				}
			}
		}
	}