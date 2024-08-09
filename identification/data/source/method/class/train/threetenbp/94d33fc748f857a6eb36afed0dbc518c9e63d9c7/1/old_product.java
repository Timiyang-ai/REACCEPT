public TemporalAccessor parseBest(CharSequence text, TemporalQuery<?>... types) {
        Objects.requireNonNull(text, "text");
        Objects.requireNonNull(types, "types");
        if (types.length < 2) {
            throw new IllegalArgumentException("At least two types must be specified");
        }
        try {
            DateTimeBuilder builder = parseToBuilder(text, null).resolve();
            for (TemporalQuery<?> type : types) {
                try {
                    return (TemporalAccessor) builder.build(type);
                } catch (RuntimeException ex) {
                    // continue
                }
            }
            throw new DateTimeException("Unable to convert parsed text to any specified type: " + Arrays.toString(types));
        } catch (DateTimeParseException ex) {
            throw ex;
        } catch (RuntimeException ex) {
            throw createError(text, ex);
        }
    }