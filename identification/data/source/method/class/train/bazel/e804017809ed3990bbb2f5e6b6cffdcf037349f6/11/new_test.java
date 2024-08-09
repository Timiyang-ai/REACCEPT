  @Test
  public void withConfiguredAspects_BasicAccessors() throws Exception {
    update();
    AspectDescriptor simpleAspect = new AspectDescriptor(TestAspects.SIMPLE_ASPECT);
    AspectDescriptor attributeAspect = new AspectDescriptor(TestAspects.ATTRIBUTE_ASPECT);
    AspectCollection aspects =
        AspectCollection.createForTests(ImmutableSet.of(simpleAspect, attributeAspect));
    ImmutableMap<AspectDescriptor, BuildConfiguration> twoAspectMap = ImmutableMap.of(
        simpleAspect, getTargetConfiguration(), attributeAspect, getHostConfiguration());
    Dependency targetDep =
        Dependency.withConfiguredAspects(
            Label.parseAbsolute("//a", ImmutableMap.of()),
            getTargetConfiguration(),
            aspects,
            twoAspectMap);

    assertThat(targetDep.getLabel()).isEqualTo(Label.parseAbsolute("//a", ImmutableMap.of()));
    assertThat(targetDep.hasExplicitConfiguration()).isTrue();
    assertThat(targetDep.getConfiguration()).isEqualTo(getTargetConfiguration());
    assertThat(targetDep.getAspects().getAllAspects())
        .containsExactly(simpleAspect, attributeAspect);
    assertThat(targetDep.getAspectConfiguration(simpleAspect)).isEqualTo(getTargetConfiguration());
    assertThat(targetDep.getAspectConfiguration(attributeAspect))
        .isEqualTo(getHostConfiguration());

    assertThrows(IllegalStateException.class, () -> targetDep.getTransition());
  }