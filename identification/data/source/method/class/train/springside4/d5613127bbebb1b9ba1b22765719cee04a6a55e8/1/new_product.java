public static long convertSizeBytes(String size) {
		Matcher matcher = NUMBER_AND_UNIT.matcher(size);

		if (matcher.matches()) {
			long number = Long.parseLong(matcher.group(1));

			if (matcher.group(2) != null) {
				char unit = matcher.group(2).toLowerCase().charAt(0);

				switch (unit) {
				case 'b':
					return number;
				case 'k':
					return number * 1024;
				case 'm':
					return number * 1024 * 1024;
				case 'g':
					return number * 1024 * 1024 * 1024;
				case 't':
					return number * 1024 * 1024 * 1024 * 1024;
				default:
					throw new IllegalArgumentException("unknown size unit :" + unit);
				}
			} else {
				return number;
			}
		} else {
			throw new IllegalArgumentException("malformed size string: " + size);
		}
	}