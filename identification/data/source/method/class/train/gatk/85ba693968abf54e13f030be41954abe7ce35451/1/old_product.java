public static StructuralVariantContext create(final VariantContext vc) {
        if (vc instanceof StructuralVariantContext) {
            return (StructuralVariantContext) vc;
        } else {
            assertIsStructuralVariantContext(vc);
            return new StructuralVariantContext(vc);
        }
    }