public default Binding<BEAN, FIELDVALUE, TARGET> withValidator(
                SerializablePredicate<? super TARGET> predicate,
                ErrorMessageProvider errorMessageProvider) {
            return withValidator(
                    Validator.from(predicate, errorMessageProvider));
        }