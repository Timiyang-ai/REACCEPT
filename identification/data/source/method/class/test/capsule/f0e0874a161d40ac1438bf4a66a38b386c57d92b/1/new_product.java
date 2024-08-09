protected final <T extends Capsule> T sup(Class<T> caplet) {
        for (Capsule c = this; c != null; c = c.sup) {
            if (caplet.isInstance(c))
                return caplet.cast(c);
        }
        return null;
    }