public Period minusHours(long amount) {
        return (amount == Long.MIN_VALUE ? plusHours(Long.MAX_VALUE).plusHours(1) : plusHours(-amount));
    }