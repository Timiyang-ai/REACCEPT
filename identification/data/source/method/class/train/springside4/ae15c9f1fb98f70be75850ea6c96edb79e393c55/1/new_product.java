public static String formatFriendlyTimeSpanByNow(long timeStampMillis) {
		long now = ClockUtil.currentTimeMillis();
		long span = now - timeStampMillis;
		if (span < 0) {
			// 'c' 日期和时间，被格式化为 "%ta %tb %td %tT %tZ %tY"，例如 "Sun Jul 20 16:17:00 EDT 1969"。
			return String.format("%tc", timeStampMillis);
		}
		if (span < DateUtil.MILLIS_PER_SECOND) {
			return "刚刚";
		} else if (span < DateUtil.MILLIS_PER_MINUTE) {
			return String.format("%d秒前", span / DateUtil.MILLIS_PER_SECOND);
		} else if (span < DateUtil.MILLIS_PER_HOUR) {
			return String.format("%d分钟前", span / DateUtil.MILLIS_PER_MINUTE);
		}
		// 获取当天00:00
		long wee = DateUtil.beginOfDate(new Date(now)).getTime();
		if (timeStampMillis >= wee) {
			// 'R' 24 小时制的时间，被格式化为 "%tH:%tM"
			return String.format("今天%tR", timeStampMillis);
		} else if (timeStampMillis >= wee - DateUtil.MILLIS_PER_DAY) {
			return String.format("昨天%tR", timeStampMillis);
		} else {
			// 'F' ISO 8601 格式的完整日期，被格式化为 "%tY-%tm-%td"。
			return String.format("%tF", timeStampMillis);
		}
	}