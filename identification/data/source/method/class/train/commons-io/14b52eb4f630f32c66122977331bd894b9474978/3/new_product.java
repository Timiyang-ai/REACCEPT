public ValidatingObjectInputStream reject(final String... patterns) {
        for (final String pattern : patterns) {
            rejectMatchers.add(new WildcardClassNameMatcher(pattern));
        }
        return this;
    }