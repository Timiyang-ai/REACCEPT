public static boolean matchRequiredVersions(String version, String value) {
		try {
			/*
			 * If "value" is not within range specified by "version", then a ModuleException will be thrown.
			 * Otherwise, just return true at last.
			 */
			checkRequiredVersion(version, value);
			return true;
		}
		catch (ModuleException e) {
			return false;
		}
	}