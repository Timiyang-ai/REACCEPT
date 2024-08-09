public TemporalAccessor parseBest(CharSequence text, TemporalQuery<?>... queries) {
        Objects.requireNonNull(text, "text");
        Objects.requireNonNull(queries, "queries");
        if (queries.length < 2) {
            throw new IllegalArgumentException("At least two queries must be specified");
        }
        String str = text.toString();  // parsing whole String, so this makes sense
        try {
            DateTimeBuilder builder = parseToBuilder(str).resolve();
            for (TemporalQuery<?> query : queries) {
                try {
                    return (TemporalAccessor) builder.query(query);
                } catch (RuntimeException ex) {
                    // continue
                }
            }
            throw new DateTimeException("Unable to convert parsed text using any of the specified queries");
        } catch (DateTimeParseException ex) {
            throw ex;
        } catch (RuntimeException ex) {
            throw createError(str, ex);
        }
    }