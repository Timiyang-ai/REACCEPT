public long betweenYear(boolean isReset) {
		final Calendar beginCal = DateUtil.calendar(begin);
		final Calendar endCal = DateUtil.calendar(end);

		int result = endCal.get(Calendar.YEAR) - beginCal.get(Calendar.YEAR);
		if (false == isReset) {
			// 考虑闰年的2月情况
			if(Calendar.FEBRUARY == beginCal.get(Calendar.MONTH) && Calendar.FEBRUARY == endCal.get(Calendar.MONTH)){
				if(beginCal.get(Calendar.DAY_OF_MONTH) == beginCal.getActualMaximum(Calendar.DAY_OF_MONTH)
				&& endCal.get(Calendar.DAY_OF_MONTH) == endCal.getActualMaximum(Calendar.DAY_OF_MONTH)){
					// 两个日期都位于2月的最后一天，此时月数按照相等对待，此时都设置为1号
					beginCal.set(Calendar.DAY_OF_MONTH, 1);
					endCal.set(Calendar.DAY_OF_MONTH, 1);
				}
			}

			endCal.set(Calendar.YEAR, beginCal.get(Calendar.YEAR));
			long between = endCal.getTimeInMillis() - beginCal.getTimeInMillis();
			if (between < 0) {
				return result - 1;
			}
		}
		return result;
	}