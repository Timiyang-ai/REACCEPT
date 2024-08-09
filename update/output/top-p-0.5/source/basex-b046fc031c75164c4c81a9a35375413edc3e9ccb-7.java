@Test
public void pathToNative() {
  // Ensure the test uses a valid, accessible path
  String tempDir = System.getProperty("java.io.tmpdir");
  String testFileName = "testFile.txt";
  String testFilePath = Paths.get(tempDir, testFileName).toString();

  // Create a temporary file to ensure the path exists
  File testFile = new File(testFilePath);
  try {
    boolean created = testFile.createNewFile();
    if (!created && !testFile.exists()) {
      fail("Test setup failed: unable to create or find the test file.");
    }

    // Perform the test
    String nativePathResult = query(_FILE_PATH_TO_NATIVE.args(testFilePath));
    String expectedPath = testFile.toPath().toRealPath().toString();

    // Clean up the test file
    boolean deleted = testFile.delete();
    if (!deleted) {
      System.err.println("Warning: Failed to delete the test file.");
    }

    // Assert the native path conversion was correct
    assertEquals("The native path conversion did not return the expected result.", expectedPath, nativePathResult);
  } catch (IOException e) {
    fail("An IOException occurred during the test: " + e.getMessage());
  }
}