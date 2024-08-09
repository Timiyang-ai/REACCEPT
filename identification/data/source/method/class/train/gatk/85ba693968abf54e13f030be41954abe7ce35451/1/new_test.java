@Test(dataProvider="validVariantContexts")
    public void testOf(final VariantContext vc, @SuppressWarnings("unused") final ReferenceMultiSource reference) {
        final StructuralVariantContext svc = StructuralVariantContext.of(vc);
        Assert.assertNotNull(svc);
    }