public final boolean isRegistered() {
        return !canonicalName.startsWith("x-") && !canonicalName.startsWith("X-");
    }