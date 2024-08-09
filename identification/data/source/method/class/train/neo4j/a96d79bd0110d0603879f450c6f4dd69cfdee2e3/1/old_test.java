@Test
	public void testGetEdges() {
		String script="results.add(g.E)";
		Transaction tx= null;
		Representation curRepresentationObj = null;
		try
		{
			tx=curGraphDBServiceObj.beginTx();
			curRepresentationObj=(Representation)curGremlinPluginObj.getEdges(script, curGraphDBServiceObj);
			assertNotNull(curRepresentationObj);
			tx.success();
			
			System.err.println("GremlinPluginTest::testGetEdges the contents of the representation object="+curRepresentationObj.toString());
		}
		catch (Throwable t) {
			t.printStackTrace ();
		}
		finally
		{
			tx.finish();
		}
		
	}