@Deprecated
    public void setMetadata(String field, String value) throws MissingResourceException {
        if ((field.trim()).equals("name") && (value == null || value.trim().equals("")))
        {
            try
            {
                value = I18nUtil.getMessage("org.dspace.workflow.WorkflowManager.untitled");
            }
            catch (MissingResourceException e)
            {
                value = "Untitled";
            }
        }

        String[] MDValue = getMDValueByLegacyField(field);

        /*
         * Set metadata field to null if null
         * and trim strings to eliminate excess
         * whitespace.
         */
		if(value == null)
        {
            clearMetadata(MDValue[0], MDValue[1], MDValue[2], Item.ANY);
            modifiedMetadata = true;
        }
        else
        {
            setMetadataSingleValue(MDValue[0], MDValue[1], MDValue[2], null, value);
        }

        addDetails(field);
    }