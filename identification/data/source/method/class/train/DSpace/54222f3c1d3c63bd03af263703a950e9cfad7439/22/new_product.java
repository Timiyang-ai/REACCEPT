public static String getLicenseText(Locale locale, Collection collection,
            Item item, EPerson eperson, Map<String, Object> additionalInfo)
    {
        Formatter formatter = new Formatter(locale);

        // EPerson firstname, lastname, email and the current date
        // will be available as separate arguments to make more simple produce
        // "tradition" text license
        // collection, item and eperson object will be also available
        int numArgs = 7 + (additionalInfo != null ? additionalInfo.size() : 0);
        Object[] args = new Object[numArgs];
        args[0] = eperson.getFirstName();
        args[1] = eperson.getLastName();
        args[2] = eperson.getEmail();
        args[3] = new java.util.Date();
        args[4] = new FormattableArgument("collection", collection);
        args[5] = new FormattableArgument("item", item);
        args[6] = new FormattableArgument("eperson", eperson);

        if (additionalInfo != null)
        {
            int i = 7; // Start is next index after previous args
            for (Map.Entry<String, Object> info : additionalInfo.entrySet())
            {
                args[i] = new FormattableArgument(info.getKey(), info.getValue());
                i++;
            }
        }

        String licenseTemplate = collectionService.getLicense(collection);

        return formatter.format(licenseTemplate, args).toString();
    }