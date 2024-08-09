public RingbufferConfig getAsReadOnly() {
        return new RingbufferConfigReadOnly(this);
    }