public default Binding<BEAN, FIELDVALUE, TARGET> withValidator(
                SerializablePredicate<? super TARGET> predicate,
                String message) {
            return withValidator(Validator.from(predicate, message));
        }