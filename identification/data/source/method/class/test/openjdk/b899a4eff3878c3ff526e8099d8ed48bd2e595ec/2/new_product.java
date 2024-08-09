public static Duration parse(CharSequence text) {
        Objects.requireNonNull(text, "text");
        Matcher matcher = PATTERN.matcher(text);
        if (matcher.matches()) {
            // check for letter T but no time sections
            if ("T".equals(matcher.group(3)) == false) {
                boolean negate = "-".equals(matcher.group(1));
                String dayMatch = matcher.group(2);
                String hourMatch = matcher.group(4);
                String minuteMatch = matcher.group(5);
                String secondMatch = matcher.group(6);
                String fractionMatch = matcher.group(7);
                if (dayMatch != null || hourMatch != null || minuteMatch != null || secondMatch != null) {
                    long daysAsSecs = parseNumber(text, dayMatch, SECONDS_PER_DAY, "days");
                    long hoursAsSecs = parseNumber(text, hourMatch, SECONDS_PER_HOUR, "hours");
                    long minsAsSecs = parseNumber(text, minuteMatch, SECONDS_PER_MINUTE, "minutes");
                    long seconds = parseNumber(text, secondMatch, 1, "seconds");
                    int nanos = parseFraction(text,  fractionMatch, seconds < 0 ? -1 : 1);
                    try {
                        return create(negate, daysAsSecs, hoursAsSecs, minsAsSecs, seconds, nanos);
                    } catch (ArithmeticException ex) {
                        throw (DateTimeParseException) new DateTimeParseException("Text cannot be parsed to a Duration: overflow", text, 0).initCause(ex);
                    }
                }
            }
        }
        throw new DateTimeParseException("Text cannot be parsed to a Duration", text, 0);
    }