  @Test
  public void stream() {
    LegalEntityCurveGroup test = LegalEntityCurveGroup.of(NAME1, REPO_CURVES, ISSUER_CURVES);
    List<Curve> expectedAll = ImmutableList.<Curve>builder()
        .add(REPO_CURVE)
        .add(ISSUER_CURVE1)
        .add(ISSUER_CURVE2)
        .add(ISSUER_CURVE3)
        .build();
    test.stream().collect(Collectors.toList()).containsAll(expectedAll);
    List<Curve> expectedRepo = ImmutableList.<Curve>builder()
        .add(REPO_CURVE)
        .build();
    test.repoCurveStream().collect(Collectors.toList()).containsAll(expectedRepo);
    List<Curve> expectedIssuer = ImmutableList.<Curve>builder()
        .add(ISSUER_CURVE1)
        .add(ISSUER_CURVE2)
        .add(ISSUER_CURVE3)
        .build();
    test.issuerCurveStream().collect(Collectors.toList()).containsAll(expectedIssuer);
  }