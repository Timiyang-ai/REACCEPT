@Override
    public T get() {
        if (!isEvaluated()) {
            synchronized (this) {
                if (!isEvaluated()) {
                    value = supplier.get();
                    supplier = null; // free mem
                }
            }
        }
        return value;
    }