  @Test
  public void test_nextVersion_expect_preserveUnderscores() {

    assertThat(MigrationVersion.parse("2").nextVersion()).isEqualTo("3");
    assertThat(MigrationVersion.parse("1.0").nextVersion()).isEqualTo("1.1");
    assertThat(MigrationVersion.parse("2.0.b34").nextVersion()).isEqualTo("2.1");
    assertThat(MigrationVersion.parse("1.1.1_2__Foo").nextVersion()).isEqualTo("1.1.1_3");
    assertThat(MigrationVersion.parse("1.1.1.2_junk").nextVersion()).isEqualTo("1.1.1.3");
    assertThat(MigrationVersion.parse("1_2.3_4__Foo").nextVersion()).isEqualTo("1_2.3_5");
    assertThat(MigrationVersion.parse("1_2.3_4_").nextVersion()).isEqualTo("1_2.3_5");
    assertThat(MigrationVersion.parse("1_2_3_4__Foo").nextVersion()).isEqualTo("1_2_3_5");
  }