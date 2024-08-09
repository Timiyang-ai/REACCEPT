	@Test
	public void canCreate()
	{	
		// Triple.ANY
		when( evaluator.getPrincipal() ).thenReturn( PRINCIPAL );
		when( evaluator.evaluate( anyObject(), any(SecurityEvaluator.Action.class), any(Node.class), any(Triple.class))).thenReturn( Boolean.TRUE);
		assertTrue( securedItemImpl.canCreate( Triple.ANY ) );
		verify( evaluator ).evaluate( principal.capture(), action.capture(), modelNode.capture(), triple.capture());
		
		Triple t = triple.getValue();
		assertEquals( Node.ANY, t.getSubject() );
		assertEquals( Node.ANY, t.getPredicate() );
		assertEquals( Node.ANY, t.getObject() );
		
		Node n = modelNode.getValue();
		assertEquals( NodeFactory.createURI( "urn:name" ), n );
		
		Object p = principal.getValue();
		assertEquals( PRINCIPAL, p );
		
		Action a = action.getValue();
		assertEquals( Action.Create, a );
		
		reset( evaluator );
		
		// FUTURE ANY Variable
		when( evaluator.getPrincipal() ).thenReturn( PRINCIPAL );
		when( evaluator.evaluate( anyObject(), any(SecurityEvaluator.Action.class), any(Node.class), any(Triple.class))).thenReturn( Boolean.TRUE);
		Triple target = new Triple( SecurityEvaluator.FUTURE, Node.ANY, Var.alloc( "var"));
		assertTrue( securedItemImpl.canCreate( target ) );
		verify( evaluator ).evaluate( principal.capture(), action.capture(), modelNode.capture(), triple.capture());
		
		t = triple.getValue();
		assertEquals( SecurityEvaluator.FUTURE, t.getSubject() );
		assertEquals( Node.ANY, t.getPredicate() );
		assertEquals( SecurityEvaluator.VARIABLE, t.getObject() );
		
		n = modelNode.getValue();
		assertEquals( NodeFactory.createURI( "urn:name" ), n );
		
		p = principal.getValue();
		assertEquals( PRINCIPAL, p );
		
		a = action.getValue();
		assertEquals( Action.Create, a );
	}