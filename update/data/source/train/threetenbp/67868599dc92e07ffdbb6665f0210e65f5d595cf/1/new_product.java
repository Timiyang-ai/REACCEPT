@Override
    public long get(DateTimeField field) {
        if (field instanceof LocalDateTimeField) {
            switch ((LocalDateTimeField) field) {
                case DAY_OF_MONTH: return day;
                case MONTH_OF_YEAR: return month.getValue();
            }
            throw new CalendricalException(field.getName() + " not valid for MonthDay");
        }
        return field.get(this);
    }