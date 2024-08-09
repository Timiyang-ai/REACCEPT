  @Test
  public void withConfigurationAndAspects_BasicAccessors() throws Exception {
    update();
    AspectDescriptor simpleAspect = new AspectDescriptor(TestAspects.SIMPLE_ASPECT);
    AspectDescriptor attributeAspect = new AspectDescriptor(TestAspects.ATTRIBUTE_ASPECT);
    AspectCollection twoAspects = AspectCollection.createForTests(
        ImmutableSet.of(simpleAspect, attributeAspect));
    Dependency targetDep =
        Dependency.withConfigurationAndAspects(
            Label.parseAbsolute("//a", ImmutableMap.of()), getTargetConfiguration(), twoAspects);

    assertThat(targetDep.getLabel()).isEqualTo(Label.parseAbsolute("//a", ImmutableMap.of()));
    assertThat(targetDep.hasExplicitConfiguration()).isTrue();
    assertThat(targetDep.getConfiguration()).isEqualTo(getTargetConfiguration());
    assertThat(targetDep.getAspects()).isEqualTo(twoAspects);
    assertThat(targetDep.getAspectConfiguration(simpleAspect)).isEqualTo(getTargetConfiguration());
    assertThat(targetDep.getAspectConfiguration(attributeAspect))
        .isEqualTo(getTargetConfiguration());

    assertThrows(IllegalStateException.class, () -> targetDep.getTransition());
  }