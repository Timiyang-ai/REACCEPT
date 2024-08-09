@SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes")
    public static void htmlize(CharSequence q, StringBuilder out) {
        try {
            htmlize(q, (Appendable) out);
        } catch (IOException ioe) {
            // StringBuilder's append methods are not declared to throw
            // IOException, so this should never happen.
            throw new RuntimeException("StringBuilder should not throw IOException", ioe);
        }
    }