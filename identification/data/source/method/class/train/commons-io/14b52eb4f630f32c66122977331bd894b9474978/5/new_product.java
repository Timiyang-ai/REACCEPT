public ValidatingObjectInputStream reject(final ClassNameMatcher m) {
        rejectMatchers.add(m);
        return this;
    }