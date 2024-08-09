  @Test
  public void withConfiguration_BasicAccessors() throws Exception {
    update();
    Dependency targetDep =
        Dependency.withConfiguration(
            Label.parseAbsolute("//a", ImmutableMap.of()), getTargetConfiguration());

    assertThat(targetDep.getLabel()).isEqualTo(Label.parseAbsolute("//a", ImmutableMap.of()));
    assertThat(targetDep.hasExplicitConfiguration()).isTrue();
    assertThat(targetDep.getConfiguration()).isEqualTo(getTargetConfiguration());
    assertThat(targetDep.getAspects().getAllAspects()).isEmpty();

    assertThrows(IllegalStateException.class, () -> targetDep.getTransition());
  }