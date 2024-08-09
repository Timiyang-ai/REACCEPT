	@Test
	public void canUpdate()
	{	
		// Triple.ANY
		when( evaluator.getPrincipal() ).thenReturn( PRINCIPAL );
		when( evaluator.evaluateUpdate( anyObject(), any(Node.class), any(Triple.class), any(Triple.class))).thenReturn( Boolean.TRUE);
		assertTrue( securedItemImpl.canUpdate( Triple.ANY, Triple.ANY ) );
		verify( evaluator ).evaluateUpdate( principal.capture(), modelNode.capture(), triple.capture(), any());
		
		Triple t = triple.getValue();
		assertEquals( Node.ANY, t.getSubject() );
		assertEquals( Node.ANY, t.getPredicate() );
		assertEquals( Node.ANY, t.getObject() );
		
		Node n = modelNode.getValue();
		assertEquals( NodeFactory.createURI( "urn:name" ), n );
		
		Object p = principal.getValue();
		assertEquals( PRINCIPAL, p );
		
		reset( evaluator );
		
		// FUTURE ANY Variable
		when( evaluator.getPrincipal() ).thenReturn( PRINCIPAL );
		when( evaluator.evaluateUpdate( anyObject(), any(Node.class), any(Triple.class), any(Triple.class))).thenReturn( Boolean.TRUE);
		Triple target = new Triple( SecurityEvaluator.FUTURE, Node.ANY, Var.alloc( "var"));
		assertTrue( securedItemImpl.canUpdate( target, Triple.ANY ) );
		verify( evaluator ).evaluateUpdate( principal.capture(), modelNode.capture(), triple.capture(), any());
		
		t = triple.getValue();
		assertEquals( SecurityEvaluator.FUTURE, t.getSubject() );
		assertEquals( Node.ANY, t.getPredicate() );
		assertEquals( SecurityEvaluator.VARIABLE, t.getObject() );
		
		n = modelNode.getValue();
		assertEquals( NodeFactory.createURI( "urn:name" ), n );
		
		p = principal.getValue();
		assertEquals( PRINCIPAL, p );
	}