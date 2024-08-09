public static HighAvailabilityMode fromConfig(Configuration config) {
		String haMode = config.getValue(HighAvailabilityOptions.HA_MODE);

		if (haMode == null) {
			return HighAvailabilityMode.NONE;
		} else if (haMode.equalsIgnoreCase(ConfigConstants.DEFAULT_RECOVERY_MODE)) {
			// Map old default to new default
			return HighAvailabilityMode.NONE;
		} else {
			try {
				return HighAvailabilityMode.valueOf(haMode.toUpperCase());
			} catch (IllegalArgumentException e) {
				return FACTORY_CLASS;
			}
		}
	}