public void setReadOnly(boolean fieldsReadOnly) {
        getBindings().stream().map(BindingImpl::getField)
                .forEach(field -> field.setReadOnly(fieldsReadOnly));
    }