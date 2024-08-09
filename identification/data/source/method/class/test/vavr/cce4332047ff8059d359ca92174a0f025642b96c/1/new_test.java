    private Try<String> success() {
        return Try.of(() -> "ok");
    }