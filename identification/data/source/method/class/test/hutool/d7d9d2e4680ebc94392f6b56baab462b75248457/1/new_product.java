public static DateTime parse(String dateStr) {
		if(null == dateStr) {
			return null;
		}
		dateStr = dateStr.trim();
		int length = dateStr.length();
		try {
			if(length == NORM_DATETIME_PATTERN.length()) {
				return parseDateTime(dateStr);
			}else if(length == NORM_DATE_PATTERN.length()) {
				return parseDate(dateStr);
			}else if(length == NORM_TIME_PATTERN.length()){
				return parseTime(dateStr);
			}else if(length == NORM_DATETIME_MINUTE_PATTERN.length()){
				return parse(dateStr, NORM_DATETIME_MINUTE_PATTERN);
			}else if(length == NORM_DATETIME_MS_PATTERN.length()){
				return parse(dateStr, NORM_DATETIME_MS_PATTERN);
			}
		}catch(Exception e) {
			throw new UtilException(StrUtil.format("Parse [{}] with format normal error!", dateStr));
		}
		
		//没有更多匹配的时间格式
		throw new UtilException(StrUtil.format(" [{}] format is not fit for date pattern!", dateStr));
	}