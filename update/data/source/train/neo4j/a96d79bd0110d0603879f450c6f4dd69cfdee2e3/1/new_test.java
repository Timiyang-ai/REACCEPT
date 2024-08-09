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
		}
		catch (Throwable t) {
			t.printStackTrace ();
		}
		finally
		{
			tx.finish();
		}
		System.out.println("Results of testGetEdges"+json.format( curRepresentationObj ));
	}