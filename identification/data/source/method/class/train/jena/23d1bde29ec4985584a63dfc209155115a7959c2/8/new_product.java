private static Triple convert(
			final Triple jenaTriple )
	{
		if (jenaTriple.getSubject().isVariable() || jenaTriple.getPredicate().isVariable() || jenaTriple.getObject().isVariable())
		{ 
			return new Triple(SecuredItemImpl.convert(jenaTriple.getSubject()),	
				SecuredItemImpl.convert(jenaTriple.getPredicate()),
				SecuredItemImpl.convert(jenaTriple.getObject()));
		}
		return jenaTriple;
	}