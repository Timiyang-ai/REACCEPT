public DCValue[] getMetadata(String schema, String element, String qualifier, String lang) {
        try
        {
            BrowseItemDAO dao = BrowseDAOFactory.getItemInstance(ourContext);

            // if the qualifier is a wildcard, we have to get it out of the
            // database
            if (Item.ANY.equals(qualifier))
            {
                try {
                    return dao.queryMetadata(id, schema, element, qualifier, lang);
                } catch (SQLException e) {
                    log.error("caught exception: ", e);
                }
            }

            if (!metadata.isEmpty())
            {
                List<DCValue> values = new ArrayList<DCValue>();
                Iterator<DCValue> i = metadata.iterator();

                while (i.hasNext())
                {
                    DCValue dcv = i.next();

                    if (match(schema, element, qualifier, lang, dcv))
                    {
                        values.add(dcv);
                    }
                }

                if (values.isEmpty())
                {
                    DCValue[] dcvs = new DCValue[0];
                    try {
                        dcvs = dao.queryMetadata(id, schema, element, qualifier, lang);
                    } catch (SQLException e) {
                        log.error("caught exception: ", e);
                    }
                    if (dcvs != null)
                    {
                    	Collections.addAll(metadata, dcvs);
                    }
                    return dcvs;
                }

                // else, Create an array of matching values
                DCValue[] valueArray = new DCValue[values.size()];
                valueArray = (DCValue[]) values.toArray(valueArray);

                return valueArray;
            }
            else
            {
                DCValue[] dcvs = new DCValue[0];
                try {
                    dcvs = dao.queryMetadata(id, schema, element, qualifier, lang);
                } catch (SQLException e) {
                    log.error("caught exception: ", e);
                }
                if (dcvs != null)
                {
                	Collections.addAll(metadata, dcvs);
                }
                return dcvs;
            }
        }
        catch (BrowseException be)
        {
            log.error("caught exception: ", be);
            return null;
        }
    }