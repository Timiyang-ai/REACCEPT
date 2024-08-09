@Override
    public long get(DateTimeField field) {
        if (field instanceof LocalDateTimeField) {
            return date.get(field);
        }
        return field.doGet(this);
    }