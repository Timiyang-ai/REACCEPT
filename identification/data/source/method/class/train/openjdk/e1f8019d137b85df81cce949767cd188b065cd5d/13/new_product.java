public Instant truncatedTo(TemporalUnit unit) {
        if (unit == ChronoUnit.NANOS) {
            return this;
        }
        Duration unitDur = unit.getDuration();
        if (unitDur.getSeconds() > LocalTime.SECONDS_PER_DAY) {
            throw new DateTimeException("Unit is too large to be used for truncation");
        }
        long dur = unitDur.toNanos();
        if ((LocalTime.NANOS_PER_DAY % dur) != 0) {
            throw new DateTimeException("Unit must divide into a standard day without remainder");
        }
        long nod = (seconds % LocalTime.SECONDS_PER_DAY) * LocalTime.NANOS_PER_SECOND + nanos;
        long result = Jdk8Methods.floorDiv(nod, dur) * dur;
        return plusNanos(result - nod);
    }