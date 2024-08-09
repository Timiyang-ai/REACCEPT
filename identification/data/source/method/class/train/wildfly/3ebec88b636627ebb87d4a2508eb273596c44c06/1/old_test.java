@Test
 	public void testInvalidateQuery() throws Exception {

 		SFSB2LC sfsb = lookup("SFSB2LC", SFSB2LC.class);
 		String message = sfsb.invalidateQuery();
 		
 		if (!message.equals("OK")){
 			fail(message);
 		}
 	}