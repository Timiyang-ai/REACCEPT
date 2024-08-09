public static void delete(Context ctx, int type, int id, String handle, String[] identifiers)
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