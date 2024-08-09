  public static <T extends Throwable> void assertMessage(Class<T> expectedType,
      Executable executable, String message) {
    T x = assertThrows(expectedType, executable);
    if (message != null) {
      assertEquals(message, x.getMessage());
    }
  }