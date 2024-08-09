public default Binding<BEAN, TARGET> withValidator(
                SerializablePredicate<? super TARGET> predicate,
                ErrorMessageProvider errorMessageProvider) {
            return withValidator(
                    Validator.from(predicate, errorMessageProvider));
        }