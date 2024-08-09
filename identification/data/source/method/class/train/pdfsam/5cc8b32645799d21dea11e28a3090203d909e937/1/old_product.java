public static <T> FXValidationSupport<T> alwaysValid() {
        return new FXValidationSupport<>(new Validator<T>() {

            public boolean isValid(T input) {
                return true;
            }
        });
    }