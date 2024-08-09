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
        // double check that it has a date accessioned and date issued, and if either of those dates
        // are not set then set them to today.
        DCDate now = DCDate.getCurrent();
        
        // If the item doesn't have a date.accessioned, create one.
        DCValue[] dateAccessioned = item.getDC("date", "accessioned", Item.ANY);
        if (dateAccessioned.length == 0)
        {
	        item.addDC("date", "accessioned", null, now.toString());
        }
        
        // create issue date if not present
        DCValue[] currentDateIssued = item.getDC("date", "issued", Item.ANY);
        if (currentDateIssued.length == 0)
        {
            DCDate issued = new DCDate(now.getYear(),now.getMonth(),now.getDay(),-1,-1,-1);
            item.addDC("date", "issued", null, issued.toString());
        }
        
        // Record that the item was restored
		String provDescription = "Restored into DSpace on "+ now + " (GMT).";
		item.addDC("description", "provenance", "en", provDescription);

        return finishItem(c, item, is);
    }