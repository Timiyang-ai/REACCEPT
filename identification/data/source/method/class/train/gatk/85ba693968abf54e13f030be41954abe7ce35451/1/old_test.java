@Test(dataProvider="validVariantContexts")
    public void testCreate(final VariantContext vc, @SuppressWarnings("unused") final ReferenceMultiSource reference) {
        final StructuralVariantContext svc = StructuralVariantContext.create(vc);
        Assert.assertNotNull(svc);
    }