public Bundle[] getBundles() throws SQLException
    {
        // Get the bundle table rows
        TableRowIterator tri = DatabaseManager.queryTable(ourContext, "bundle",
                "SELECT bundle.* FROM bundle, bundle2bitstream WHERE " +
                        "bundle.bundle_id=bundle2bitstream.bundle_id AND " +
                        "bundle2bitstream.bitstream_id= ? ",
                bRow.getIntColumn("bitstream_id"));

        // Build a list of Bundle objects
        List<Bundle> bundles = new ArrayList<Bundle>();
        try
        {
            while (tri.hasNext())
            {
                TableRow r = tri.next();

                // First check the cache
                Bundle fromCache = (Bundle) ourContext.fromCache(Bundle.class, r
                        .getIntColumn("bundle_id"));

                if (fromCache != null)
                {
                    bundles.add(fromCache);
                }
                else
                {
                    bundles.add(new Bundle(ourContext, r));
                }
            }
        }
        finally
        {
            // close the TableRowIterator to free up resources
            if (tri != null)
            {
                tri.close();
            }
        }

        Bundle[] bundleArray = new Bundle[bundles.size()];
        bundleArray = (Bundle[]) bundles.toArray(bundleArray);

        return bundleArray;
    }