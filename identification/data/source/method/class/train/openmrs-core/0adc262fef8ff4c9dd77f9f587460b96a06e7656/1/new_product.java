public Date addToDate(Date startDate, OrderFrequency frequency) {
		if (SNOMED_CT_SECONDS_CODE.equals(code)) {
			return addSeconds(startDate, this.duration);
		}
		if (SNOMED_CT_MINUTES_CODE.equals(code)) {
			return addMinutes(startDate, this.duration);
		}
		if (SNOMED_CT_HOURS_CODE.equals(code)) {
			return addHours(startDate, this.duration);
		}
		if (SNOMED_CT_DAYS_CODE.equals(code)) {
			return addDays(startDate, this.duration);
		}
		if (SNOMED_CT_WEEKS_CODE.equals(code)) {
			return addWeeks(startDate, this.duration);
		}
		if (SNOMED_CT_MONTHS_CODE.equals(code)) {
			return addMonths(startDate, this.duration);
		}
		if (SNOMED_CT_YEARS_CODE.equals(code)) {
			return addYears(startDate, this.duration);
		}
		if (SNOMED_CT_RECURRING_INTERVAL_CODE.equals(code)) {
			if (frequency == null) {
				throw new APIException("Frequency can not be null when duration in Recurring Interval");
			}
			return addSeconds(startDate, (int) (this.duration * SECONDS_PER_DAY / frequency.getFrequencyPerDay()));
		} else {
			throw new APIException(String.format("Unknown code '%s' for SNOMED CT duration units", code));
		}
	}