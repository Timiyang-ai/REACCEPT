public final boolean isRegistered() {
        return !name.startsWith("X-") && !name.startsWith("x-");
    }