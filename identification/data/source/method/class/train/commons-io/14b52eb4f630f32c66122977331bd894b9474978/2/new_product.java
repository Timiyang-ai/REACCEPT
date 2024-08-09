public ValidatingObjectInputStream reject(final String... patterns) {
        for (String pattern : patterns) {
            rejectMatchers.add(new WildcardClassNameMatcher(pattern));
        }
        return this;
    }