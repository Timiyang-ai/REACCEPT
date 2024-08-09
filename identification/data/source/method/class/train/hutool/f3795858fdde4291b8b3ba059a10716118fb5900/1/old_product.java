public long betweenMonth(boolean isReset) {
		final Calendar beginCal = DateUtil.calendar(begin);
		final Calendar endCal = DateUtil.calendar(end);

		final int betweenYear = endCal.get(Calendar.YEAR) - beginCal.get(Calendar.YEAR);
		final int betweenMonthOfYear = endCal.get(Calendar.MONTH) - beginCal.get(Calendar.MONTH);

		int result = betweenYear * 12 + betweenMonthOfYear;
		if (isReset && beginCal.get(Calendar.DAY_OF_MONTH) > endCal.get(Calendar.DAY_OF_MONTH)) {
			// 在非重置情况下，如果起始日期的天小于结束日期的天，月数要少算1（不足1个月）
			return result - 1;
		}
		return result;
	}