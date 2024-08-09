public static String getModuleAddress (String ApplicationRoot, String moduleId, String userId)
	{
		log.debug("*** Getter.getModuleAddress ***");
		String output = new String();
		String type = new String();
		Connection conn = Database.getCoreConnection(ApplicationRoot);
		try
		{
			CallableStatement callstmt = conn.prepareCall("call moduleGetHash(?, ?)");
			callstmt.setString(1, moduleId);
			callstmt.setString(2, userId);
			log.debug("Gathering moduleGetHash ResultSet");
			ResultSet modules = callstmt.executeQuery();
			log.debug("Opening Result Set from moduleGetHash");
			modules.next(); //Exception thrown if no hash was found
			//Set Type. Used to ensure the URL points at the correct directory
			if(modules.getString(3).equalsIgnoreCase("challenge"))
			{
				type = "challenges";
			}
			else
			{
				type = "lessons";
			}
			output = type + "/" + modules.getString(1) + ".jsp";
		}
		catch(Exception e)
		{
			log.error("Module Hash Retrieval: " + e.toString());
		}
		Database.closeConnection(conn);
		log.debug("*** END getModuleAddress() ***");
		return output;
	}