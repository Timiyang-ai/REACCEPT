public boolean canCreate( final org.apache.jena.graph.Triple t )
	{
		return canCreate(SecuredItemImpl.convert(t));
	}