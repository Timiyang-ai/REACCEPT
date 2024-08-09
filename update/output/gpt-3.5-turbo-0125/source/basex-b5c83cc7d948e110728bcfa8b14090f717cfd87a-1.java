@Test
public void resolvePath() {
    final File path = new File(PATH1);
    final String can = path.getAbsoluteFile().getPath();
    final String pathResult = resolvePath(createQueryContext(), path).getString();
    assertEquals(pathResult.toLowerCase(Locale.ENGLISH), can.toLowerCase(Locale.ENGLISH));
}