public DateTimeAccessor parseBest(CharSequence text, Class<?>... types) {
        DateTimes.checkNotNull(text, "Text must not be null");
        DateTimes.checkNotNull(types, "Class array must not be null");
        if (types.length < 2) {
            throw new IllegalArgumentException("At least two types must be specified");
        }
        String str = text.toString();  // parsing whole String, so this makes sense
        try {
            DateTimeBuilder builder = parseToBuilder(str).resolve();
            for (Class<?> type : types) {
                try {
                    return (DateTimeAccessor) builder.build(type);
                } catch (RuntimeException ex) {
                    // continue
                }
            }
            throw new DateTimeException("Unable to convert parsed text to any specified type: " + Arrays.toString(types));
        } catch (DateTimeParseException ex) {
            throw ex;
        } catch (RuntimeException ex) {
            throw createError(str, ex);
        }
    }