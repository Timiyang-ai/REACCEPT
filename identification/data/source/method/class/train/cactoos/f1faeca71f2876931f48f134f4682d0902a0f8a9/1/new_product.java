@Override
    @SuppressWarnings("PMD.OnlyOneReturn")
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextEnvelope)) {
            return false;
        }
        final TextEnvelope that = (TextEnvelope) obj;
        return new UncheckedText(this).asString().equals(
            new UncheckedText(that).asString()
        );
    }