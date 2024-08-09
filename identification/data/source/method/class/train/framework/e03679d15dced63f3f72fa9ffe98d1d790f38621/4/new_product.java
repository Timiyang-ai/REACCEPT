public static <T> Validator<T> alwaysPass() {
        return (value, context) -> ValidationResult.ok();
    }