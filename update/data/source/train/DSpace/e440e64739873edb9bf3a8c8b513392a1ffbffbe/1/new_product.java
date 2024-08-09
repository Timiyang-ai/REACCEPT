public static Item restoreItem(Context c, InProgressSubmission is,
            String suppliedHandle)
        throws SQLException, IOException, AuthorizeException
    {
        Item item = is.getItem();

        IdentifierService identifierService = new DSpace().getSingletonService(IdentifierService.class);
        try {
            if(suppliedHandle == null)
            {
                identifierService.register(c, item);
            }else{
                identifierService.register(c, item, suppliedHandle);
            }
        } catch (IdentifierException e) {
            throw new RuntimeException("Can't create an Identifier!");
        }

        // Even though we are restoring an item it may not have the proper dates. So let's
        // double check its associated date(s)
        DCDate now = DCDate.getCurrent();
        
        // If the item doesn't have a date.accessioned, set it to today
        Metadatum[] dateAccessioned = item.getDC("date", "accessioned", Item.ANY);
        if (dateAccessioned.length == 0)
        {
	        item.addDC("date", "accessioned", null, now.toString());
        }
        
        // If issue date is set as "today" (literal string), then set it to current date
        // In the below loop, we temporarily clear all issued dates and re-add, one-by-one,
        // replacing "today" with today's date.
        // NOTE: As of DSpace 4.0, DSpace no longer sets an issue date by default
        Metadatum[] currentDateIssued = item.getDC("date", "issued", Item.ANY);
        item.clearDC("date", "issued", Item.ANY);
        for (Metadatum dcv : currentDateIssued)
        {
            if(dcv.value!=null && dcv.value.equalsIgnoreCase("today"))
            {
                DCDate issued = new DCDate(now.getYear(),now.getMonth(),now.getDay(),-1,-1,-1);
                item.addDC(dcv.element, dcv.qualifier, dcv.language, issued.toString());
            }
            else if(dcv.value!=null)
            {
                item.addDC(dcv.element, dcv.qualifier, dcv.language, dcv.value);
            }
        }
        
        // Record that the item was restored
        String provDescription = "Restored into DSpace on "+ now + " (GMT).";
        item.addDC("description", "provenance", "en", provDescription);

        return finishItem(c, item, is);
    }