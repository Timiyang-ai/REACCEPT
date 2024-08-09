public static boolean isBirthday(int year, int month, int day) {
		// 验证年
		int thisYear = DateUtil.thisYear();
		if (year < 1900 || year > thisYear) {
			return false;
		}

		// 验证月
		if (month < 1 || month > 12) {
			return false;
		}

		// 验证日
		if (day < 1 || day > 31) {
			return false;
		}
		if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
			return false;
		}
		if (month == 2) {
			if (day > 29 || (day == 29 && false == DateUtil.isLeapYear(year))) {
				return false;
			}
		}
		return true;
	}