public Metadatum[] getMetadata(String schema, String element, String qualifier, String lang) {
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
                List<Metadatum> values = new ArrayList<Metadatum>();
                Iterator<Metadatum> i = metadata.iterator();

                while (i.hasNext())
                {
                    Metadatum dcv = i.next();

                    if (match(schema, element, qualifier, lang, dcv))
                    {
                        values.add(dcv);
                    }
                }

                if (values.isEmpty())
                {
                    Metadatum[] dcvs = new Metadatum[0];
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
                Metadatum[] valueArray = new Metadatum[values.size()];
                valueArray = (Metadatum[]) values.toArray(valueArray);

                return valueArray;
            }
            else
            {
                Metadatum[] dcvs = new Metadatum[0];
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