@Override
    public Registration addValueChangeListener(
            HasValue.ValueChangeListener<? super Set<T>> listener) {
        return addSelectionListener(
                event -> listener.accept(new ValueChange<>(event.getConnector(),
                        event.getValue(), event.isUserOriginated())));
    }