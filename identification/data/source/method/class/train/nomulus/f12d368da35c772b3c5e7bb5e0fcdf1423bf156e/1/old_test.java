@Test
  public void testCreateReportFiles_multipleFiles_noHint() throws Exception {
    Path root = toNormalizedPath(folder.newFolder("my", "root"));
    File destination = folder.newFolder("my", "root", "some", "path");
    folder.newFolder("my", "root", "some", "path", "a", "b");
    folder.newFolder("my", "root", "some", "path", "c");

    Files.write(
        folder.newFile("my/root/some/path/index.html").toPath(), "some data".getBytes(UTF_8));
    Files.write(
        folder.newFile("my/root/some/path/a/index.html").toPath(), "wrong index".getBytes(UTF_8));
    Files.write(
        folder.newFile("my/root/some/path/c/style.css").toPath(), "css file".getBytes(UTF_8));
    Files.write(
        folder.newFile("my/root/some/path/my_image.png").toPath(), "images".getBytes(UTF_8));

    ReportFiles files = createReportFiles(destination, Optional.empty(), root);

    assertThat(files.entryPoint().toString()).isEqualTo("some/path/unimplemented.txt");
    assertThat(readAllFiles(files).keySet()).containsExactly("some/path/unimplemented.txt");
  }