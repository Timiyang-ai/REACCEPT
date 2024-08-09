public long betweenMonth(boolean isReset) {
		final Calendar beginCal = DateUtil.calendar(begin);
		final Calendar endCal = DateUtil.calendar(end);

		final int betweenYear = endCal.get(Calendar.YEAR) - beginCal.get(Calendar.YEAR);
		final int betweenMonthOfYear = endCal.get(Calendar.MONTH) - beginCal.get(Calendar.MONTH);

		int result = betweenYear * 12 + betweenMonthOfYear;
		if (false == isReset) {
			endCal.set(Calendar.YEAR, beginCal.get(Calendar.YEAR));
			endCal.set(Calendar.MONTH, beginCal.get(Calendar.MONTH));
			long between = endCal.getTimeInMillis() - beginCal.getTimeInMillis();
			if (between < 0) {
				return result - 1;
			}
		}
		return result;
	}