@Override
    public Registration addValueChangeListener(
            HasValue.ValueChangeListener<Set<T>> listener) {
        return addSelectionListener(event -> listener.accept(
                new ValueChangeEvent<>(this, event.isUserOriginated())));
    }