@Test
  public void testRecordDouble() {
    viewManager.registerView(RpcViewConstants.RPC_CLIENT_ROUNDTRIP_LATENCY_VIEW);
    View beforeView =
        viewManager.getView(
            RpcViewConstants.RPC_CLIENT_ROUNDTRIP_LATENCY_VIEW);
    beforeView.match(
        new Function<DistributionView, Void>() {
          @Override
          public Void apply(DistributionView view) {
            assertThat(view.getDistributionAggregations()).isEmpty();
            return null;
          }
        },
        new Function<IntervalView, Void>() {
          @Override
          public Void apply(IntervalView view) {
            fail("Expected a DistributionView");
            return null;
          }
        });
    StatsContext context =
        defaultStatsContext.with(
            RpcMeasurementConstants.RPC_CLIENT_METHOD, TagValue.create("myMethod"));
    MeasureMap measurements =
        MeasureMap.builder()
            .set((DoubleMeasure) RpcMeasurementConstants.RPC_CLIENT_ROUNDTRIP_LATENCY, 5.1).build();
    context.record(measurements);
    View afterView =
        viewManager.getView(
            RpcViewConstants.RPC_CLIENT_ROUNDTRIP_LATENCY_VIEW);
    afterView.match(
        new Function<DistributionView, Void>() {
          @Override
          public Void apply(DistributionView view) {
            assertThat(view.getDistributionAggregations()).hasSize(1);
            DistributionAggregation agg = view.getDistributionAggregations().get(0);
            assertThat(agg.getTags())
                .containsExactly(
                    Tag.create(
                        RpcMeasurementConstants.RPC_CLIENT_METHOD, TagValue.create("myMethod")));
            assertThat(agg.getCount()).isEqualTo(1);
            assertThat(agg.getMean()).isWithin(TOLERANCE).of(5.1);
            return null;
          }
        },
        new Function<IntervalView, Void>() {
          @Override
          public Void apply(IntervalView view) {
            fail("Expected a DistributionView");
            return null;
          }
        });
  }