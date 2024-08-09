public static boolean matchRequiredVersions(String version, String versionRange) {
		if (versionRange != null && !versionRange.equals("")) {
			String[] ranges = versionRange.split(",");
			for (String range : ranges) {
				// need to externalize this string
				String separator = "-";
				if (range.indexOf("*") > 0 || range.indexOf(separator) > 0 && (!isVersionWithQualifier(range))) {
					// if it contains "*" or "-" then we must separate those two
					// assume it's always going to be two part
					// assign the upper and lower bound
					// if there's no "-" to split lower and upper bound
					// then assign the same value for the lower and upper
					String lowerBound = range;
					String upperBound = range;
					
					int indexOfSeparator = range.indexOf(separator);
					while (indexOfSeparator > 0) {
						lowerBound = range.substring(0, indexOfSeparator);
						upperBound = range.substring(indexOfSeparator + 1);
						if (upperBound.matches("^\\s?\\d+.*"))
							break;
						indexOfSeparator = range.indexOf(separator, indexOfSeparator + 1);
					}
					
					// only preserve part of the string that match the following format:
					// - xx.yy.*
					// - xx.yy.zz*
					lowerBound = StringUtils.remove(lowerBound, lowerBound.replaceAll("^\\s?\\d+[\\.\\d+\\*?|\\.\\*]+", ""));
					upperBound = StringUtils.remove(upperBound, upperBound.replaceAll("^\\s?\\d+[\\.\\d+\\*?|\\.\\*]+", ""));
					
					// if the lower contains "*" then change it to zero
					if (lowerBound.indexOf("*") > 0)
						lowerBound = lowerBound.replaceAll("\\*", "0");
					
					// if the upper contains "*" then change it to maxRevisionNumber
					if (upperBound.indexOf("*") > 0)
						upperBound = upperBound.replaceAll("\\*", Integer.toString(Integer.MAX_VALUE));
					
					int lowerReturn = compareVersion(version, lowerBound);
					
					int upperReturn = compareVersion(version, upperBound);
					
					if (lowerReturn < 0 || upperReturn > 0) {
						log.debug("Version " + version + " is not between " + lowerBound + " and " + upperBound);
					} else {
						return true;
					}
				} else {
					if (compareVersion(version, range) < 0) {
						log.debug("Version " + version + " is below " + range);
					} else {
						return true;
					}
				}
			}
		}
		return false;
	}