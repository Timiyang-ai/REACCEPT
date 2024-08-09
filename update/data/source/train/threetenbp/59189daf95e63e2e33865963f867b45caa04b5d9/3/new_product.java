@Override
    public MonthDay with(DateTimeField field, long newValue) {
        if (field instanceof LocalDateTimeField) {
            LocalDateTimeField f = (LocalDateTimeField) field;
            f.checkValidValue(newValue);
            switch (f) {
                case DAY_OF_MONTH: return withDayOfMonth((int) newValue);
                case MONTH_OF_YEAR: return  withMonth((int) newValue);
            }
            throw new CalendricalException(field.getName() + " not valid for MonthDay");
        }
        return field.set(this, newValue);
    }