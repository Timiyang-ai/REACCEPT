public static int compareVersion(String version, String value) {
		try {
			Integer val = new Integer(value.replace(".", ""));
			Integer ver = new Integer(version.replace(".", ""));
			return (ver.compareTo(val));
		} catch (NumberFormatException e) {
			log.error("Error while converting a version to an integer", e);
			return 0;
		}
	}