public static DateTime parse(CharSequence dateCharSequence) {
		if (StrUtil.isBlank(dateCharSequence)) {
			return null;
		}
		String dateStr = dateCharSequence.toString();
		// 去掉两边空格并去掉中文日期中的“日”和“秒”，以规范长度
		dateStr = StrUtil.removeAll(dateStr.trim(), '日', '秒');
		int length = dateStr.length();

		if (NumberUtil.isNumber(dateStr)) {
			// 纯数字形式
			if (length == DatePattern.PURE_DATETIME_PATTERN.length()) {
				return parse(dateStr, DatePattern.PURE_DATETIME_FORMAT);
			} else if (length == DatePattern.PURE_DATETIME_MS_PATTERN.length()) {
				return parse(dateStr, DatePattern.PURE_DATETIME_MS_FORMAT);
			} else if (length == DatePattern.PURE_DATE_PATTERN.length()) {
				return parse(dateStr, DatePattern.PURE_DATE_FORMAT);
			} else if (length == DatePattern.PURE_TIME_PATTERN.length()) {
				return parse(dateStr, DatePattern.PURE_TIME_FORMAT);
			}
		} else if (ReUtil.isMatch(PatternPool.TIME, dateStr)) {
			// HH:mm:ss 或者 HH:mm 时间格式匹配单独解析
			return parseTimeToday(dateStr);
		} else if (StrUtil.containsAnyIgnoreCase(dateStr, wtb)) {
			// JDK的Date对象toString默认格式，类似于：
			// Tue Jun 4 16:25:15 +0800 2019
			// Thu May 16 17:57:18 GMT+08:00 2019
			// Wed Aug 01 00:00:00 CST 2012
			return parseCST(dateStr);
		} else if (StrUtil.contains(dateStr, 'T')) {
			// UTC时间
			return parseUTC(dateStr);
		}

		if (length == DatePattern.NORM_DATETIME_PATTERN.length()) {
			// yyyy-MM-dd HH:mm:ss
			return parseDateTime(dateStr);
		} else if (length == DatePattern.NORM_DATE_PATTERN.length()) {
			// yyyy-MM-dd
			return parseDate(dateStr);
		} else if (length == DatePattern.NORM_DATETIME_MINUTE_PATTERN.length()) {
			// yyyy-MM-dd HH:mm
			return parse(normalize(dateStr), DatePattern.NORM_DATETIME_MINUTE_FORMAT);
		} else if (length >= DatePattern.NORM_DATETIME_MS_PATTERN.length() - 2) {
			return parse(normalize(dateStr), DatePattern.NORM_DATETIME_MS_FORMAT);
		}

		// 没有更多匹配的时间格式
		throw new DateException("No format fit for date String [{}] !", dateStr);
	}