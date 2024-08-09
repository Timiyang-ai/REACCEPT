public String getFormatDescription()
    {
        if (bitstreamFormat.getShortDescription().equals("Unknown"))
        {
            // Get user description if there is one
            String desc = getUserFormatDescription();

            if (desc == null)
            {
                return "Unknown";
            }

            return desc;
        }

        // not null or Unknown
        return bitstreamFormat.getShortDescription();
    }