public static <T> Validator<T> alwaysPass() {
        return v -> Result.ok(v);
    }