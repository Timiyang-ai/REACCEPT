public static boolean isBirthday(String value) {
		if (isMactchRegex(BIRTHDAY, value)) {
			int year = Integer.parseInt(value.substring(0, 4));
			int month = Integer.parseInt(value.substring(5, 7));
			int day = Integer.parseInt(value.substring(8, 10));

			if (month < 1 || month > 12) {
				return false;
			}
			if (day < 1 || day > 31) {
				return false;
			}
			if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
				return false;
			}
			if (month == 2) {
				boolean isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
				if (day > 29 || (day == 29 && !isleap)) {
					return false;
				}
			}
		}
		return true;
	}