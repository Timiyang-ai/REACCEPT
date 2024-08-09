  @NonNull private ErrorHandler createTestErrorHandler() {
    return new ErrorHandler() {
      @Override public void handleError(Exception exception, String message) {
      }
    };
  }