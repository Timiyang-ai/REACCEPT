public ValidatingObjectInputStream reject(Class<?>... classes) {
        for (Class<?> c : classes) {
            rejectMatchers.add(new FullClassNameMatcher(c.getName()));
        }
        return this;
    }