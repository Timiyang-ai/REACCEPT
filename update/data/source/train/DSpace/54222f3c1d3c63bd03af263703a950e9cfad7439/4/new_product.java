public static void delete(Context ctx, int type, UUID id, String handle, List<String> identifiers)
            throws SQLException, RDFMissingIdentifierException
    {
        String uri = RDFConfiguration.getURIGenerator().generateIdentifier(ctx,
                        type, id, handle, identifiers);
        if (uri != null)
        {
            RDFConfiguration.getRDFStorage().delete(uri);
        } else {
            throw new RDFMissingIdentifierException(type, id);
        }
    }