  @Test
  public void export_succeeds() throws IOException {
    File sqlFile = tempFolder.newFile();
    exporter.export(ImmutableList.of(TestEntity.class), sqlFile);
    assertThat(Files.readAllBytes(sqlFile.toPath()))
        .isEqualTo(
            ("\n"
                    + "    create table \"TestEntity\" (\n"
                    + "       name text not null,\n"
                    + "        cu text,\n"
                    + "        primary key (name)\n"
                    + "    );\n")
                .getBytes(StandardCharsets.UTF_8));
  }