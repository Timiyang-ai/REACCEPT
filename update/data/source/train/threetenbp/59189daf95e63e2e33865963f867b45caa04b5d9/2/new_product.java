@Override
    public long get(DateTimeField field) {
        if (field instanceof LocalDateTimeField) {
            switch ((LocalDateTimeField) field) {

                case DAY_OF_WEEK: return isoDate.getDayOfWeek().getValue();
                case DAY_OF_MONTH: return isoDate.getDayOfMonth();
                case DAY_OF_YEAR: return isoDate.getDayOfYear();
                case MONTH_OF_YEAR: return isoDate.getMonth().getValue();
                case YEAR_OF_ERA: return (isoDate.getYear() >= 1 ? isoDate.getYear() : 1 - isoDate.getYear());
                case YEAR: return isoDate.getYear();
                case ERA: return (isoDate.getYear() >= 1 ? 1 : 0);
            }
            throw new CalendricalException(field.getName() + " not valid for LocalDate");
        }
        return field.get(this);
    }