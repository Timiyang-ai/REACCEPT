@Test
public void updateEntries() throws IOException {
  check(_ZIP_UPDATE_ENTRIES);
  String list = query(_ZIP_ENTRIES.args(ZIP));

  // create and compare identical zip file
  query(_ZIP_UPDATE_ENTRIES.args(list, TMPZIP));
  final String list2 = query(_ZIP_ENTRIES.args(TMPZIP));
  assertEquals(list.replaceAll(" href=\".*?\"", ""),
      list2.replaceAll(" href=\".*?\"", ""));

  // remove one directory
  list = list.replaceAll("<zip:dir name=.test.>.*</zip:dir>", "");
  query(_ZIP_UPDATE_ENTRIES.args(list, TMPZIP));

  // new file has no entries
  list = list.replaceAll("<zip:dir.*</zip:dir>", "");
  error(_ZIP_UPDATE_ENTRIES.args(list,
      new File(TMPZIP).getCanonicalPath()), Err.ZIP_FAIL);
}