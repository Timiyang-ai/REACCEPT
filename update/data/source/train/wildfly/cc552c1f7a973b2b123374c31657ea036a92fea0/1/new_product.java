public static PatchOperationBuilder rollback(final String patchId, final boolean rollbackTo, final boolean resetConfiguration) {
            return new AbstractOperationBuilder() {
                @Override
                public ModelNode execute(PatchOperationTarget target) throws IOException {
                    return target.rollback(patchId, this, rollbackTo, resetConfiguration);
                }
            };
        }