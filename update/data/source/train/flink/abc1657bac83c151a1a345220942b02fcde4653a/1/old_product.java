public static HighAvailabilityMode fromConfig(Configuration config) {
		String haMode = ConfigurationUtil.getStringWithDeprecatedKeys(
				config,
				ConfigConstants.HA_MODE,
				null,
				ConfigConstants.RECOVERY_MODE);

		if (haMode == null) {
			return HighAvailabilityMode.NONE;
		} else if (haMode.equalsIgnoreCase(ConfigConstants.DEFAULT_RECOVERY_MODE)) {
			// Map old default to new default
			return HighAvailabilityMode.NONE;
		} else {
			return HighAvailabilityMode.valueOf(haMode.toUpperCase());
		}
	}