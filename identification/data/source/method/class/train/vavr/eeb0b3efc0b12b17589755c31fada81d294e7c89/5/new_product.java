@Override
    public T get() {
        return (supplier == null) ? value : computeValue();
    }