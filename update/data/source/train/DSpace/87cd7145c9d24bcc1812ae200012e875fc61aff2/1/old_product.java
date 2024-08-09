public void setMetadata(String field, String value) throws MissingResourceException
    {
        if ((field.trim()).equals("name")
                && (value == null || value.trim().equals("")))
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

        /*
         * Set metadata field to null if null
         * and trim strings to eliminate excess
         * whitespace.
         */
		if(value == null)
        {
            collectionRow.setColumnNull(field);
        }
        else
        {
            collectionRow.setColumn(field, value.trim());
        }

        modifiedMetadata = true;
        addDetails(field);
    }