  @Test
  public void test_load_all_curves() {
    ListMultimap<LocalDate, RatesCurveGroup> allGroups = RatesCurvesCsvLoader.loadAllDates(
        ResourceLocator.of(GROUPS_1),
        ResourceLocator.of(SETTINGS_1),
        ImmutableList.of(ResourceLocator.of(CURVES_1), ResourceLocator.of(CURVES_2), ResourceLocator.of(CURVES_3)));

    assertThat(allGroups.size()).isEqualTo(2);
    assertCurves(allGroups.get(CURVE_DATE));

    List<RatesCurveGroup> curves3 = allGroups.get(CURVE_DATE_CURVES_3);
    assertThat(curves3).hasSize(1);
    RatesCurveGroup group = curves3.get(0);

    // All curve points are set to 0 in test data to ensure these are really different curve instances
    Curve usdDisc = group.findDiscountCurve(Currency.USD).get();
    InterpolatedNodalCurve usdDiscNodal = (InterpolatedNodalCurve) usdDisc;
    assertThat(usdDiscNodal.getMetadata().getCurveName()).isEqualTo(CurveName.of("USD-Disc"));
    assertThat(usdDiscNodal.getYValues().equalZeroWithTolerance(0d)).isTrue();

    Curve usd3ml = group.findForwardCurve(IborIndices.USD_LIBOR_3M).get();
    InterpolatedNodalCurve usd3mlNodal = (InterpolatedNodalCurve) usd3ml;
    assertThat(usd3mlNodal.getMetadata().getCurveName()).isEqualTo(CurveName.of("USD-3ML"));
    assertThat(usd3mlNodal.getYValues().equalZeroWithTolerance(0d)).isTrue();
  }