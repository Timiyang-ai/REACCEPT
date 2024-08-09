@Test
    public void testAggregate() {
        BinaryClassificationEvaluationContext<Double> ctx = new BinaryClassificationEvaluationContext<>();
        ctx.aggregate(VectorUtils.of().labeled(1.0));
        assertEquals(ctx.getFirstClassLbl(), 1., 0.);
        assertEquals(ctx.getSecondClassLbl(), null);

        ctx.aggregate(VectorUtils.of().labeled(0.0));
        assertEquals(ctx.getFirstClassLbl(), 0., 0.);
        assertEquals(ctx.getSecondClassLbl(), 1., 0.);
    }