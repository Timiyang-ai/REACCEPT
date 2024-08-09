  @Test
  public void test_fromString() throws Exception {
    PackageAndClass packageAndClass = PackageAndClass.of("foo.bar.Baz");
    assertThat(packageAndClass.packageName(), is("foo.bar"));
    assertThat(packageAndClass.className(), is("Baz"));

    packageAndClass = PackageAndClass.of("Baz");
    assertThat(packageAndClass.packageName(), is(""));
    assertThat(packageAndClass.className(), is("Baz"));
  }