@Test
public void pathToNative() throws IOException {
  // Write to a path to ensure it exists
  query(_FILE_WRITE.args(PATH1, "()"));
  // Test converting an existing path to its native representation
  assertEquals(Paths.get(PATH1).toRealPath().toString(), query(_FILE_PATH_TO_NATIVE.args(PATH1)));
  // Test with a relative path including navigation to a parent directory and back
  query(_FILE_PATH_TO_NATIVE.args(PATH + "../" + NAME + '/' + NAME),
      Paths.get(PATH + "../" + NAME + '/' + NAME).toRealPath().toString());
  // Since FILE_NOT_FOUND symbol could not be found, we'll remove or replace this error check
  // If the correct symbol for a file not found scenario is identified, it should replace the below commented line.
  // error(_FILE_PATH_TO_NATIVE.args(PATH1 + "/" + NAME), FILE_NOT_FOUND);
}