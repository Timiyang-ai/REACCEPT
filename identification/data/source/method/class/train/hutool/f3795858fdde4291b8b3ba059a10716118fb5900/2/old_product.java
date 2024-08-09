public long betweenYear(boolean isReset) {
		final Calendar beginCal = DateUtil.calendar(begin);
		final Calendar endCal = DateUtil.calendar(end);

		int result = endCal.get(Calendar.YEAR) - beginCal.get(Calendar.YEAR);
		if (isReset && beginCal.get(Calendar.MONTH) > endCal.get(Calendar.MONTH)) {
			// 在非重置情况下，如果起始日期的月小于结束日期的月，年数要少算1（不足1年）
			return result - 1;
		}
		return result;
	}