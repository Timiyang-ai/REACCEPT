public DateTimeCalendrical parseBest(CharSequence text, Class<?>... types) {
        DateTimes.checkNotNull(text, "Text must not be null");
        DateTimes.checkNotNull(types, "Class array must not be null");
        if (types.length < 2) {
            throw new IllegalArgumentException("At least two types must be specified");
        }
        String str = text.toString();  // parsing whole String, so this makes sense
        try {
            DateTimeBuilder builder = parseToBuilder(str).resolve();
            for (Class<?> type : types) {
                DateTimeCalendrical cal = (DateTimeCalendrical) builder.extract(type);
                if (cal != null) {
                    return cal;
                }
            }
            throw new CalendricalException("Unable to convert parsed text to any specified type: " + Arrays.toString(types));
        } catch (UnsupportedOperationException ex) {
            throw ex;
        } catch (CalendricalParseException ex) {
            throw ex;
        } catch (RuntimeException ex) {
            throw createError(str, ex);
        }
    }