public RingbufferConfig getAsReadOnly() {
        return new RingbufferConfigReadonly(this);
    }