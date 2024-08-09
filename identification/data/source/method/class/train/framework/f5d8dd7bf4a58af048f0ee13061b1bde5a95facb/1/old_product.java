public boolean isValid() {
        if (getBean() == null && !validators.isEmpty()) {
            throw new IllegalStateException("Cannot validate binder: "
                    + "bean level validators have been configured "
                    + "but no bean is currently set");
        }
        if (validateBindings().stream().filter(BindingValidationStatus::isError)
                .findAny().isPresent()) {
            return false;
        }
        if (getBean() != null && validateBean(getBean()).stream()
                .filter(ValidationResult::isError).findAny().isPresent()) {
            return false;
        }
        return true;
    }