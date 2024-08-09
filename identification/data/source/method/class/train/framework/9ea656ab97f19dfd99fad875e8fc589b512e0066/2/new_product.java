public default Binding<BEAN, FIELDVALUE, TARGET> withValidator(
                Predicate<? super TARGET> predicate, String message) {
            return withValidator(Validator.from(predicate, message));
        }