	public void addGraph_GSPO() {
		final Node s = NodeFactory.createURI( "s");
		final Node p = NodeFactory.createURI( "p");
		final Node o = NodeFactory.createURI( "o");

		
		WhereClause<?> whereClause = getProducer().newInstance();
		AbstractQueryBuilder<?> builder = whereClause.addGraph( "<g>", s, p, o );
		Query query = builder.build();

		ElementPathBlock epb = new ElementPathBlock();
		ElementNamedGraph eng = new ElementNamedGraph( NodeFactory.createURI("g"), epb );
		epb.addTriplePath( new TriplePath( new Triple( s, p, o)));
		
		WhereValidator visitor = new WhereValidator( eng );
		query.getQueryPattern().visit( visitor );
		assertTrue( visitor.matching );
	}