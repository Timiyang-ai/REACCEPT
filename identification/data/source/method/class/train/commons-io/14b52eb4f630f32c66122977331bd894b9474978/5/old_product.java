public ValidatingObjectInputStream reject(ClassNameMatcher m) {
        rejectMatchers.add(m);
        return this;
    }