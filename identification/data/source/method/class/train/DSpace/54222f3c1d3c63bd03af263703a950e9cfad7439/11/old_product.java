public void setFormat(BitstreamFormat f) throws SQLException
    {
        // FIXME: Would be better if this didn't throw an SQLException,
        // but we need to find the unknown format!
        if (f == null)
        {
            // Use "Unknown" format
            bitstreamFormat = BitstreamFormat.findUnknown(ourContext);
        }
        else
        {
            bitstreamFormat = f;
        }

        // Remove user type description
        clearMetadata(MetadataSchema.DC_SCHEMA,"format",null, Item.ANY);

        // Update the ID in the table row
        bRow.setColumn("bitstream_format_id", bitstreamFormat.getID());
        modified = true;
    }