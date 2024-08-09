public static boolean isBirthday(String value) {
		if(isMactchRegex(BIRTHDAY, value)){
			Matcher matcher = BIRTHDAY.matcher(value);
			if(matcher.find()){
				int year = Convert.toInt(matcher.group(1));
				int month = Convert.toInt(matcher.group(3));
				int day = Convert.toInt(matcher.group(5));
				return isBirthday(year, month, day);
			}
		}
		return false;
	}