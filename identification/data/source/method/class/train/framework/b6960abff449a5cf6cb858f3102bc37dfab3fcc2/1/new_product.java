@Override
    public void setValue(Set<T> value) {
        Objects.requireNonNull(value);
        Set<T> copy = value.stream().map(Objects::requireNonNull)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        updateSelection(copy, new LinkedHashSet<>(getSelectedItems()));
    }