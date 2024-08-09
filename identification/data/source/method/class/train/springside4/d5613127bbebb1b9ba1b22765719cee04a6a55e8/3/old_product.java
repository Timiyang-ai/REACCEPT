public static long convertDurationMillis(String duration) {
		Matcher matcher = NUMBER_AND_UNIT.matcher(duration);

		if (matcher.matches()) {
			long number = Long.valueOf(matcher.group(1));

			if (matcher.group(2) != null) {
				String unitStr = matcher.group(2).toLowerCase();
				char unit = unitStr.charAt(0);

				switch (unit) {
				case 's':
					return number * 1000;
				case 'm':
					// if it's an m, could be 'minutes' or 'millis'. default minutes
					if (unitStr.length() >= 2 && unitStr.charAt(1) == 's') {
						return number;
					}

					return number * 60 * 1000;
				case 'h':
					return number * 60 * 60 * 1000;
				case 'd':
					return number * 60 * 60 * 24 * 1000;
				case 'y':
					return number * 365 * 60 * 60 * 24 * 1000;
				default:
					throw new IllegalArgumentException("unknown time unit :" + unit);
				}
			} else {
				return number;
			}
		} else {
			throw new IllegalArgumentException("malformed duration string: " + duration);
		}
	}