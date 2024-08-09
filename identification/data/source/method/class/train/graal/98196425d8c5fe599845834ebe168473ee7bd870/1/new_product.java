@SuppressWarnings("hiding")
        /*
         * Not ready for prime time yet. We need to solve GR-18742.
         */
        Builder cpuTimeLimit(Duration timeLimit, Duration accuracy) {
            if (timeLimit == null && accuracy == null) {
                // fall through to allow reset
            } else if (timeLimit == null || accuracy == null) {
                throw new IllegalArgumentException("If a timeLimit is specified accuracy must be specified as well.");
            } else if (timeLimit.isNegative() || timeLimit.isZero()) {
                throw new IllegalArgumentException("Time limit must not be negative or zero.");
            } else if (accuracy.isNegative() || accuracy.isZero()) {
                throw new IllegalArgumentException("Accuracy must not be negative or zero.");
            }
            this.timeLimit = timeLimit;
            this.timeLimitAccuracy = accuracy;
            return this;
        }