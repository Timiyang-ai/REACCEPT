@Test
public void resolvePathUpdated() {
  // Test resolving an absolute path
  final String path1 = query(_FILE_RESOLVE_PATH.args(PATH1));
  final String can1 = new File(PATH1).getAbsoluteFile().getPath();
  assertEquals(path1.toLowerCase(Locale.ENGLISH), can1.toLowerCase(Locale.ENGLISH));

  // Additional test: ensuring directory resolution remains consistent with the new implementation
  final String dirPath = PATH1.endsWith(File.separator) ? PATH1 : PATH1 + File.separator;
  final String path2 = query(_FILE_RESOLVE_PATH.args(dirPath));
  final String can2 = new File(dirPath).getAbsoluteFile().getPath();
  assertEquals(path2.toLowerCase(Locale.ENGLISH), can2.toLowerCase(Locale.ENGLISH));
}