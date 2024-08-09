public final void setShortDescription(String s)
       throws SQLException
    {
        // You can not reset the unknown's registry's name
        BitstreamFormat unknown = null;
		try {
			unknown = findUnknown(bfContext);
		} catch (IllegalStateException e) {
			// No short_description='Unknown' found in bitstreamformatregistry
			// table. On first load of registries this is expected because it
			// hasn't been inserted yet! So, catch but ignore this runtime 
			// exception thrown by method findUnknown.
		}
		
		// If the exception was thrown, unknown will == null so goahead and 
		// load s. If not, check that the unknown's registry's name is not
		// being reset.
		if (unknown == null || unknown.getID() != getID()) {
            bfRow.setColumn("short_description", s);
		}
    }