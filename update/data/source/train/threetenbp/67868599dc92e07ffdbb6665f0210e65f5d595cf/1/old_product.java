@Override
    public long get(DateTimeField field) {
        if (field instanceof LocalDateField) {
            switch ((LocalDateField) field) {
                case DAY_OF_MONTH: return day;
                case MONTH_OF_YEAR: return month.getValue();
            }
            throw new CalendricalException(field.getName() + " not valid for MonthDay");
        } else if (field instanceof LocalTimeField) {
            throw new CalendricalException(field.getName() + " not valid for MonthDay");
        }
        return field.get(this);
    }