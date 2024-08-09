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
		// 检查几个特殊月的最大天数
		if (day == 31 && (month == 4 || month == 6 || month == 9 || month == 11)) {
			return false;
		}
		if (month == 2) {
			// 在2月，非闰年最大28，闰年最大29
			return day < 29 || (day == 29 && DateUtil.isLeapYear(year));
		}
		return true;
	}