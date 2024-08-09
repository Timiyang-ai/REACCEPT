public BitstreamFormat getFormat(Context context) throws SQLException
    {
        return getBitstreamService().getFormat(context, this);
    }