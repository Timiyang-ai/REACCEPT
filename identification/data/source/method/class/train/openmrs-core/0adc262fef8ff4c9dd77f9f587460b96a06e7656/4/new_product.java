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
				throw new APIException("Duration.error.frequency.null", (Object[]) null);
			}
			return addSeconds(startDate, (int) (this.duration * SECONDS_PER_DAY / frequency.getFrequencyPerDay()));
		} else {
			throw new APIException("Duration.unknown.code", new Object[] { code });
		}
	}