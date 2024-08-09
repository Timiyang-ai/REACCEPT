public void setValidator(Validator<T> validator) {
        requireNotNull(validator, "Validator cannot be null");
        this.validator = validator;
        makeNotValidated();
    }