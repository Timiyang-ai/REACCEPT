@Test
    public void testAggregate() {
        BinaryClassificationEvaluationContext<Double> ctx = new BinaryClassificationEvaluationContext<>();
        ctx.aggregate(VectorUtils.of().labeled(1.0));
        assertEquals(ctx.getFirstClsLbl(), 1., 0.);
        assertEquals(ctx.getSecondClsLbl(), null);

        ctx.aggregate(VectorUtils.of().labeled(0.0));
        assertEquals(ctx.getFirstClsLbl(), 0., 0.);
        assertEquals(ctx.getSecondClsLbl(), 1., 0.);
    }