public ValidatingObjectInputStream reject(String... patterns) {
        for (String pattern : patterns) {
            rejectMatchers.add(new WildcardClassNameMatcher(pattern));
        }
        return this;
    }