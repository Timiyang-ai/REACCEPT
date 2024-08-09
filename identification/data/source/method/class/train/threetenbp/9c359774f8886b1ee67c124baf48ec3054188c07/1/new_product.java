@Override
    public long get(DateTimeField field) {
        if (field instanceof LocalDateTimeField) {
            switch ((LocalDateTimeField) field) {
                case OFFSET_SECONDS: return getOffset().getTotalSeconds();
            }
            return date.get(field);
        }
        return field.doGet(this);
    }