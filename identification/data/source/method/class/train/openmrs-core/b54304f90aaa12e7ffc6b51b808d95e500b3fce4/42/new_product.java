public static void checkRequiredVersion(String version, String versionRange) throws ModuleException {
		if (!matchRequiredVersions(version, versionRange)) {
			String ms = Context.getMessageSourceService().getMessage("Module.requireVersion.outOfBounds",
			    new String[] { versionRange, version }, Context.getLocale());
			throw new ModuleException(ms);
		}
	}