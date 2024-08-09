public static DateTime parse(String dateStr) {
		if (null == dateStr) {
			return null;
		}
		//去掉两边空格并去掉中文日期中的“日”，以规范长度
		dateStr = dateStr.trim().replace("日", "");
		int length = dateStr.length();
		
		if(Validator.isNumber(dateStr)) {
			//纯数字形式
			if(length == DatePattern.PURE_DATETIME_PATTERN.length()) {
				return parse(dateStr, DatePattern.PURE_DATETIME_FORMAT);
			} else if(length == DatePattern.PURE_DATETIME_MS_PATTERN.length()) {
				return parse(dateStr, DatePattern.PURE_DATETIME_MS_FORMAT);
			} else if(length == DatePattern.PURE_DATE_PATTERN.length()) {
				return parse(dateStr, DatePattern.PURE_DATE_FORMAT);
			} else if(length == DatePattern.PURE_TIME_PATTERN.length()) {
				return parse(dateStr, DatePattern.PURE_TIME_FORMAT);
			}
		}
		
		if (length == DatePattern.NORM_DATETIME_PATTERN.length() || length == DatePattern.NORM_DATETIME_PATTERN.length()+1) {
			return parseDateTime(dateStr);
		} else if (length == DatePattern.NORM_DATE_PATTERN.length()) {
			return parseDate(dateStr);
		} else if (length == DatePattern.NORM_TIME_PATTERN.length() || length == DatePattern.NORM_TIME_PATTERN.length()+1) {
			return parseTime(dateStr);
		} else if (length == DatePattern.NORM_DATETIME_MINUTE_PATTERN.length() || length == DatePattern.NORM_DATETIME_MINUTE_PATTERN.length()+1) {
			return parse(normalize(dateStr), DatePattern.NORM_DATETIME_MINUTE_PATTERN);
		} else if (length >= DatePattern.NORM_DATETIME_MS_PATTERN.length() - 2) {
			return parse(normalize(dateStr), DatePattern.NORM_DATETIME_MS_PATTERN);
		}

		// 没有更多匹配的时间格式
		throw new DateException("No format fit for date String [{}] !", dateStr);
	}