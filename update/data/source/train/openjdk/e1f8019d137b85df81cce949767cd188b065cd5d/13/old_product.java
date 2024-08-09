public String getText(Chronology chrono, TemporalField field, long value,
                                    TextStyle style, Locale locale) {
        if (chrono == IsoChronology.INSTANCE
                || !(field instanceof ChronoField)) {
            return getText(field, value, style, locale);
        }

        int fieldIndex;
        int fieldValue;
        if (field == ERA) {
            fieldIndex = Calendar.ERA;
            if (chrono == JapaneseChronology.INSTANCE) {
                if (value == -999) {
                    fieldValue = 0;
                } else {
                    fieldValue = (int) value + 2;
                }
            } else {
                fieldValue = (int) value;
            }
        } else if (field == MONTH_OF_YEAR) {
            fieldIndex = Calendar.MONTH;
            fieldValue = (int) value - 1;
        } else if (field == DAY_OF_WEEK) {
            fieldIndex = Calendar.DAY_OF_WEEK;
            fieldValue = (int) value + 1;
            if (fieldValue > 7) {
                fieldValue = Calendar.SUNDAY;
            }
        } else if (field == AMPM_OF_DAY) {
            fieldIndex = Calendar.AM_PM;
            fieldValue = (int) value;
        } else {
            return null;
        }
        return CalendarDataUtility.retrieveCldrFieldValueName(
                chrono.getCalendarType(), fieldIndex, fieldValue, toStyle(style), locale);
    }