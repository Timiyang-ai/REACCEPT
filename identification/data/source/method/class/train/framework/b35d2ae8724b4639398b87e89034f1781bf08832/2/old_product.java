public void setReadOnly(boolean fieldsReadOnly) {
        getBindings().stream()
            .filter(binding -> Objects.nonNull(binding.setter))
            .map(BindingImpl::getField)
                .forEach(field -> field.setReadOnly(fieldsReadOnly));
    }