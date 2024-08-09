private void compare(Item item, String[] fromCSV, boolean change,
                         String md, BulkEditChange changes, DSpaceCSVLine line) throws SQLException, AuthorizeException
    {
        // Log what metadata element we're looking at
        String all = "";
        for (String part : fromCSV)
        {
            all += part + ",";
        }
        all = all.substring(0, all.length());
        log.debug(LogManager.getHeader(c, "metadata_import",
                                       "item_id=" + item.getID() + ",fromCSV=" + all));

        // Don't compare collections  or actions
        if (("collection".equals(md)) || ("action".equals(md)))
        {
            return;
        }

        // Make a String array of the current values stored in this element
        // First, strip of language if it is there
        String language = null;
        if (md.contains("["))
        {
            String[] bits = md.split("\\[");
            language = bits[1].substring(0, bits[1].length() - 1);
        }

        AuthorityValue fromAuthority = getAuthorityValueType(md);
        if (md.indexOf(':') > 0) {
            md = md.substring(md.indexOf(':') + 1);
        }

        String[] bits = md.split("\\.");
        String schema = bits[0];
        String element = bits[1];
        // If there is a language on the element, strip if off
        if (element.contains("["))
        {
            element = element.substring(0, element.indexOf('['));
        }
        String qualifier = null;
        if (bits.length > 2)
        {
            qualifier = bits[2];

            // If there is a language, strip if off
            if (qualifier.contains("["))
            {
                qualifier = qualifier.substring(0, qualifier.indexOf('['));
            }
        }
        log.debug(LogManager.getHeader(c, "metadata_import",
                                       "item_id=" + item.getID() + ",fromCSV=" + all +
                                       ",looking_for_schema=" + schema +
                                       ",looking_for_element=" + element +
                                       ",looking_for_qualifier=" + qualifier +
                                       ",looking_for_language=" + language));
        String[] dcvalues = new String[0];
        if(fromAuthority==null) {
            Metadatum[] current = item.getMetadata(schema, element, qualifier, language);
            dcvalues = new String[current.length];
            int i = 0;
            for (Metadatum dcv : current) {
                if (dcv.authority == null || !isAuthorityControlledField(md)) {
                    dcvalues[i] = dcv.value;
                } else {
                    dcvalues[i] = dcv.value + DSpaceCSV.authoritySeparator + dcv.authority;
                    dcvalues[i] += DSpaceCSV.authoritySeparator + (dcv.confidence != -1 ? dcv.confidence : Choices.CF_ACCEPTED);
                }
                i++;
                log.debug(LogManager.getHeader(c, "metadata_import",
                        "item_id=" + item.getID() + ",fromCSV=" + all +
                                ",found=" + dcv.value));
            }
        }else{
            dcvalues = line.get(md).toArray(new String[line.get(md).size()]);
        }

        // Compare from current->csv
        for (int v = 0; v < fromCSV.length; v++) {
            String value = fromCSV[v];
            Metadatum dcv = getDcValueFromCSV(language, schema, element, qualifier, value, fromAuthority);
            if (fromAuthority!=null) {
                value = dcv.value + DSpaceCSV.authoritySeparator + dcv.authority + DSpaceCSV.authoritySeparator + dcv.confidence;
                fromCSV[v] = value;
            }

            if ((value != null) && (!"".equals(value)) && (!contains(value, dcvalues))) {
                changes.registerAdd(dcv);
            } else {
                // Keep it
                changes.registerConstant(dcv);
            }
        }

        // Compare from csv->current
        for (String value : dcvalues)
        {
            // Look to see if it should be removed
            Metadatum dcv = new Metadatum();
            dcv.schema = schema;
            dcv.element = element;
            dcv.qualifier = qualifier;
            dcv.language = language;
            if (value == null || value.indexOf(DSpaceCSV.authoritySeparator) < 0)
                simplyCopyValue(value, dcv);
            else
            {
                String[] parts = value.split(DSpaceCSV.escapedAuthoritySeparator);
                dcv.value = parts[0];
                dcv.authority = parts[1];
                dcv.confidence = (parts.length > 2 ? Integer.valueOf(parts[2]) : Choices.CF_ACCEPTED);
            }

            if ((value != null) && (!"".equals(value)) && (!contains(value, fromCSV)) && fromAuthority==null)
            // fromAuthority==null: with the current implementation metadata values from external authority sources can only be used to add metadata, not to change or remove them
            // because e.g. an author that is not in the column "ORCID:dc.contributor.author" could still be in the column "dc.contributor.author" so don't remove it
            {
                // Remove it
                log.debug(LogManager.getHeader(c, "metadata_import",
                                               "item_id=" + item.getID() + ",fromCSV=" + all +
                                               ",removing_schema=" + schema +
                                               ",removing_element=" + element +
                                               ",removing_qualifier=" + qualifier +
                                               ",removing_language=" + language));
                changes.registerRemove(dcv);
            }
        }

        // Update the item if it has changed
        if ((change) &&
            ((changes.getAdds().size() > 0) || (changes.getRemoves().size() > 0)))
        {
            // Get the complete list of what values should now be in that element
            List<Metadatum> list = changes.getComplete();
            List<String> values = new ArrayList<String>();
            List<String> authorities = new ArrayList<String>();
            List<Integer> confidences = new ArrayList<Integer>();
            for (Metadatum value : list)
            {
                if ((qualifier == null) && (language == null))
                {
                    if ((schema.equals(value.schema)) &&
                        (element.equals(value.element)) &&
                         (value.qualifier == null) &&
                         (value.language == null))
                    {
                        values.add(value.value);
                        authorities.add(value.authority);
                        confidences.add(value.confidence);
                    }
                }
                else if (qualifier == null)
                {
                    if ((schema.equals(value.schema)) &&
                        (element.equals(value.element)) &&
                        (language.equals(value.language)) &&
                        (value.qualifier == null))
                    {
                        values.add(value.value);
                        authorities.add(value.authority);
                        confidences.add(value.confidence);
                    }
                }
                else if (language == null)
                {
                    if ((schema.equals(value.schema)) &&
                        (element.equals(value.element)) &&
                        (qualifier.equals(value.qualifier)) &&
                        (value.language == null))
                    {
                        values.add(value.value);
                        authorities.add(value.authority);
                        confidences.add(value.confidence);
                    }
                }
                else
                {
                    if ((schema.equals(value.schema)) &&
                        (element.equals(value.element)) &&
                        (qualifier.equals(value.qualifier)) &&
                        (language.equals(value.language)))
                    {
                        values.add(value.value);
                        authorities.add(value.authority);
                        confidences.add(value.confidence);
                    }
                }
            }

            // Set those values
            item.clearMetadata(schema, element, qualifier, language);
            String[] theValues = values.toArray(new String[values.size()]);
            String[] theAuthorities = authorities.toArray(new String[authorities.size()]);
            int[] theConfidences = new int[confidences.size()];
            for (int k=0; k< confidences.size(); k++)
            {
                theConfidences[k] = confidences.get(k).intValue();
            }
            item.addMetadata(schema, element, qualifier, language, theValues, theAuthorities, theConfidences);
            item.update();
        }
    }