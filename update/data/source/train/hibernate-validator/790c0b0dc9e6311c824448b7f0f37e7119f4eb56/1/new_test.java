@Test
	public void testGetConstrainedProperties() {
		Validator validator = TestUtil.getValidator();
		BeanDescriptor beanDescriptor = validator.getConstraintsForClass( Order.class );
		Set<PropertyDescriptor> constraintProperties = beanDescriptor.getConstrainedProperties();
		assertEquals( "There should be only one property", 1, constraintProperties.size() );
		boolean hasOrderNumber = false;
		for(PropertyDescriptor pd : constraintProperties) {
			hasOrderNumber |= pd.getPropertyName().equals( "orderNumber" );
		}
		assertTrue( "Wrong property", hasOrderNumber );


		try {
			constraintProperties.add( null );
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