public Optional<ZonedDateTime> nextExecution(ZonedDateTime date) {
        Preconditions.checkNotNull(date);
        try {
            ZonedDateTime nextMatch = nextClosestMatch(date);
            if(nextMatch.equals(date)){
                nextMatch = nextClosestMatch(date.plusSeconds(1));
            }
            return Optional.of(nextMatch);
        } catch (NoSuchValueException e) {
            return Optional.empty();
        }
    }