public static int compareVersion(String version, String value) {
		try {
			// pad the shorter version with zeros out to the length of other version
			version = version.replace(".", "0");
			value = value.replace(".", "0");
			
			while (version.length() < value.length()) {
				version = version + "0";
			}
			while (value.length() < version.length()) {
				value = value + "0";
			}
			
			// remove the 
			Integer ver = new Integer(version);
			Integer val = new Integer(value);
			return (ver.compareTo(val));
		} catch (NumberFormatException e) {
			log.error("Error while converting a version to an integer", e);
			return 0;
		}
	}