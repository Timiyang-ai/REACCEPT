@Override
    public Expression eval(final Scope scope) {
        final long now =
            scope.lookup(context, Expression.NOW).cast(IntegerExpression.class).getValue();
        final Instant nowInstant = Instant.ofEpochMilli(now);
        final LocalDateTime local = LocalDateTime.ofInstant(nowInstant, ZoneOffset.UTC);

        final int year = local.get(ChronoField.YEAR);
        final int month = local.get(ChronoField.MONTH_OF_YEAR);
        final int dayOfMonth = local.get(ChronoField.DAY_OF_MONTH);

        final Instant instant = LocalDateTime
            .of(year, month, dayOfMonth, hours, minutes, seconds, milliSeconds * 1000000)
            .toInstant(ZoneOffset.UTC);

        return new InstantExpression(context, instant);
    }