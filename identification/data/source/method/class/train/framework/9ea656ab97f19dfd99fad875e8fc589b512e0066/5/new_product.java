public default BindingBuilder<BEAN, TARGET> withValidator(
                SerializablePredicate<? super TARGET> predicate,
                String message) {
            return withValidator(Validator.from(predicate, message));
        }