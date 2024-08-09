@Test
	public void testGetConstrainedProperties() {
		Validator validator = TestUtil.getValidator();
		BeanDescriptor beanDescriptor = validator.getConstraintsForClass( Order.class );
		Set<String> constraintProperties = beanDescriptor.getConstrainedProperties();
		assertEquals( "There should be only one property", 1, constraintProperties.size() );
		assertTrue( "Wrong property", constraintProperties.contains( "orderNumber" ) );

		try {
			constraintProperties.add( "foobar" );
			fail( "Set should be immutable" );
		}
		catch ( UnsupportedOperationException e ) {

		}

		try {
			constraintProperties.remove( "orderNumber" );
			fail( "Set should be immutable" );
		}
		catch ( UnsupportedOperationException e ) {

		}
	}