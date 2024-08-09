@Test
	public void testGetModuleAddress() 
	{
		String insecureCryptoLesson = new String("201ae6f8c55ba3f3b5881806387fbf34b15c30c2");
		//Open all Modules First so that the Module Can Be Opened
		if(Setter.openAllModules(applicationRoot))
		{
			//Simulate user Opening Level
			if(!Getter.getModuleAddress(applicationRoot, insecureCryptoLesson, adminUserId).isEmpty())
			{
				log.debug("PASS: Could mark level open when level was marked as open");
			}
			else
				fail("Could not Insecure Crypto Lesson as Opened by Default admin");
		}
		else
			fail("Could not Open All Modules");
	}