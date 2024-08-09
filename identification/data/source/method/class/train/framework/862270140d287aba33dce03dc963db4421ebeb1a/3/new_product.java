@Override
    public Registration addValueChangeListener(
            HasValue.ValueChangeListener<Set<T>> listener) {
        return addSelectionListener(event -> listener.valueChange(
                new ValueChangeEvent<>(this, event.getOldValue(),
                        event.isUserOriginated())));
    }