public Date addToDate(Date startDate, OrderFrequency frequency) {
		if (SNOMED_CT_SECONDS_CODE.equals(code))
			return DateUtils.addSeconds(startDate, duration);
		if (SNOMED_CT_MINUTES_CODE.equals(code))
			return DateUtils.addMinutes(startDate, duration);
		if (SNOMED_CT_HOURS_CODE.equals(code))
			return DateUtils.addHours(startDate, duration);
		if (SNOMED_CT_DAYS_CODE.equals(code))
			return DateUtils.addDays(startDate, duration);
		if (SNOMED_CT_WEEKS_CODE.equals(code))
			return DateUtils.addWeeks(startDate, duration);
		if (SNOMED_CT_MONTHS_CODE.equals(code))
			return DateUtils.addMonths(startDate, duration);
		if (SNOMED_CT_YEARS_CODE.equals(code))
			return DateUtils.addYears(startDate, duration);
		if (SNOMED_CT_RECURRING_INTERVAL_CODE.equals(code)) {
			if (frequency == null)
				throw new APIException("Frequency can not be null when duration in Recurring Interval");
			return DateUtils.addSeconds(startDate, (int) (duration * SECONDS_PER_DAY / frequency.getFrequencyPerDay()));
		}
		throw new APIException(String.format("Unknown code '%s' for SNOMED CT duration units", code));
	}