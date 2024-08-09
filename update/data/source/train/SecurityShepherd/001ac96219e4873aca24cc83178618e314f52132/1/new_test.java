@Test
	public void testGetModuleAddress() 
	{
		String userName = new String("userGetModuleAddress");
		String insecureCryptoLesson = new String("201ae6f8c55ba3f3b5881806387fbf34b15c30c2");
		try
		{
			if(verifyTestUser(applicationRoot, userName, userName))
			{
				String userId = Getter.getUserIdFromName(applicationRoot, userName);
				//Open all Modules First so that the Module Can Be Opened
				if(Setter.openAllModules(applicationRoot))
				{
					//Simulate user Opening Level
					if(!Getter.getModuleAddress(applicationRoot, insecureCryptoLesson, userId).isEmpty())
					{
						log.debug("PASS: Could mark level open when level was marked as open");
						return;
					}
					else
						fail("Could not Insecure Crypto Lesson as Opened by user");
				}
				else
					fail("Could not Open All Modules");
			}
			else
			{
				fail("Could not verify user (No Exception Failure)");
			}
		}
		catch(Exception e)
		{
			log.fatal("Could not Verify User: " + e.toString());
			fail("Could not Verify User " + userName);
		}
	}