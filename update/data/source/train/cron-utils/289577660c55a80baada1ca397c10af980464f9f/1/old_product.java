public DateTime nextExecution(DateTime date) {
        Validate.notNull(date);
        try {
            DateTime nextMatch = nextClosestMatch(date);
            if(nextMatch.equals(date)){
                nextMatch = nextClosestMatch(date.plusSeconds(1));
            }
            return nextMatch;
        } catch (NoSuchValueException e) {
            throw new IllegalArgumentException(e);
        }
    }