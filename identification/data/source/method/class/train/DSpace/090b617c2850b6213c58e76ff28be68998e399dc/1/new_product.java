Map<String, String> crosswalkMetadata(Context context, DSpaceObject dso)
    {
        if ((null == dso) || !(dso instanceof Item))
        {
            throw new IllegalArgumentException("Must be an Item");
        }
        Item item = (Item) dso; // TODO generalize to DSO when all DSOs have metadata.

        Map<String, String> mapped = new HashMap<>();

        for (Entry<String, String> datum : crosswalk.entrySet())
        {
            List<MetadataValue> values = itemService.getMetadataByMetadataString(item, datum.getValue());
            if (null != values)
            {
                for (MetadataValue value : values)
                {
                    String key = datum.getKey();
                    String mappedValue;
                    Transform xfrm = transforms.get(key);
                    if (null != xfrm)
                    {
                        try {
                            mappedValue = xfrm.transform(value.getValue());
                        } catch (Exception ex) {
                            log.error("Unable to transform '{}' from {} to {}:  {}",
                                    new String[] {
                                        value.getValue(),
                                        value.toString(),
                                        key,
                                        ex.getMessage()
                                    });
                            continue;
                        }
                    }
                    else
                    {
                        mappedValue = value.getValue();
                    }
                    mapped.put(key, mappedValue);
                }
            }
        }

        if (GENERATE_DATACITE_XML == true)
        {
            DataCiteXMLCreator xmlGen = new DataCiteXMLCreator();
            xmlGen.setDisseminationCrosswalkName(DATACITE_XML_CROSSWALK);
            String xmlString = xmlGen.getXMLString(context, dso);
            log.debug("Generated DataCite XML:  {}", xmlString);
            mapped.put("datacite", xmlString);
        }

        // Supply a default publisher, if the Item has none.
        if (!mapped.containsKey(DATACITE_PUBLISHER)
                && !mapped.containsKey("datacite"))
        {
            String publisher = configurationService.getPropertyAsType(CFG_PUBLISHER, "unknown");
            log.info("Supplying default publisher:  {}", publisher);
            mapped.put(DATACITE_PUBLISHER, publisher);
        }

        // Supply current year as year of publication, if the Item has none.
        if (!mapped.containsKey(DATACITE_PUBLICATION_YEAR)
                && !mapped.containsKey("datacite"))
        {
            String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
            log.info("Supplying default publication year:  {}", year);
            mapped.put(DATACITE_PUBLICATION_YEAR, year);
        }

        // Supply _target link back to this object
        String handle = dso.getHandle();
        if (null == handle)
        {
            log.warn("{} #{} has no handle -- location not set.",
                    contentServiceFactory.getDSpaceObjectService(dso).getTypeText(dso), dso.getID());
        }
        else
        {
            String url = configurationService.getProperty("dspace.url")
                    + "/handle/" + item.getHandle();
            log.info("Supplying location:  {}", url);
            mapped.put("_target", url);
        }

        return mapped;
    }