@Override
    public T get() {
        if (!isDefined()) {
            synchronized (this) {
                if (!isDefined()) {
                    value = supplier.get();
                    supplier = null; // free mem
                }
            }
        }
        return value;
    }