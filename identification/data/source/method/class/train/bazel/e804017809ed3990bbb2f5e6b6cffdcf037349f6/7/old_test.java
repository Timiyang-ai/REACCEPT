  @Test
  public void withTransitionAndAspects_BasicAccessors() throws Exception {
    AspectDescriptor simpleAspect = new AspectDescriptor(TestAspects.SIMPLE_ASPECT);
    AspectDescriptor attributeAspect = new AspectDescriptor(TestAspects.ATTRIBUTE_ASPECT);
    AspectCollection twoAspects = AspectCollection.createForTests(
        ImmutableSet.of(simpleAspect, attributeAspect));
    Dependency hostDep =
        Dependency.withTransitionAndAspects(
            Label.parseAbsolute("//a", ImmutableMap.of()), HostTransition.INSTANCE, twoAspects);

    assertThat(hostDep.getLabel()).isEqualTo(Label.parseAbsolute("//a", ImmutableMap.of()));
    assertThat(hostDep.hasExplicitConfiguration()).isFalse();
    assertThat(hostDep.getAspects().getAllAspects())
        .containsExactlyElementsIn(twoAspects.getAllAspects());
    assertThat(hostDep.getTransition().isHostTransition()).isTrue();

    assertThrows(IllegalStateException.class, () -> hostDep.getConfiguration());

    assertThrows(IllegalStateException.class, () -> hostDep.getAspectConfiguration(simpleAspect));

    assertThrows(
        IllegalStateException.class, () -> hostDep.getAspectConfiguration(attributeAspect));
  }