public static DateTime parse(String dateStr) {
		if (null == dateStr) {
			return null;
		}
		dateStr = dateStr.trim();
		int length = dateStr.length();
		try {
			if (length == DatePattern.NORM_DATETIME_PATTERN.length()) {
				return parseDateTime(dateStr);
			} else if (length == DatePattern.NORM_DATE_PATTERN.length()) {
				return parseDate(dateStr);
			} else if (length == DatePattern.NORM_TIME_PATTERN.length()) {
				return parseTime(dateStr);
			} else if (length == DatePattern.NORM_DATETIME_MINUTE_PATTERN.length()) {
				return parse(dateStr, DatePattern.NORM_DATETIME_MINUTE_PATTERN);
			} else if (length >= DatePattern.NORM_DATETIME_MS_PATTERN.length() - 2) {
				return parse(dateStr, DatePattern.NORM_DATETIME_MS_PATTERN);
			}
		} catch (Exception e) {
			throw new DateException(StrUtil.format("Parse [{}] with format normal error!", dateStr));
		}

		// 没有更多匹配的时间格式
		throw new DateException(StrUtil.format(" [{}] format is not fit for date pattern!", dateStr));
	}