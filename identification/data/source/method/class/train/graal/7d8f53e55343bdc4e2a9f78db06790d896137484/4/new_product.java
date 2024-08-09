public static void neverPartOfCompilation(String message) {
        CompilerDirectives.bailout(message);
    }