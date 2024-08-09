public boolean isConsistent() {
        try {
            isConsistentOrThrow();
            return true;
        } catch (IllegalStateException x) {
            log.error(x.getMessage());
            try {
                log.error(toString());
            } catch (RuntimeException x2) {
                log.error("Printing inconsistent wallet failed", x2);
            }
            return false;
        }
    }