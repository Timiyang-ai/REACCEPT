public ValidatingObjectInputStream reject(final Class<?>... classes) {
        for (final Class<?> c : classes) {
            rejectMatchers.add(new FullClassNameMatcher(c.getName()));
        }
        return this;
    }