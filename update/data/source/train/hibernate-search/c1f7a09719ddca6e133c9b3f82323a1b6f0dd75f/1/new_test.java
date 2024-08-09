@Test
	public void dslConverter() {
		// This cast may be unsafe, but only if something is deeply wrong, and then an exception will be thrown below
		@SuppressWarnings("unchecked")
		DslConverter<V, ?> dslConverter =
				(DslConverter<V, ?>) index1FieldSchemaNode.getConverter().getDslConverter();
		DslConverter<?, ?> compatibleDslConverter =
				index2FieldSchemaNode.getConverter().getDslConverter();
		DslConverter<?, ?> incompatibleDslConverter =
				new DslConverter<>( typeDescriptor.getJavaType(), new IncompatibleToDocumentFieldValueConverter<>() );
		ToDocumentFieldValueConvertContext toDocumentConvertContext =
				new ToDocumentFieldValueConvertContextImpl( new JavaBeanBackendMappingContext() );

		// isCompatibleWith must return true when appropriate
		assertThat( dslConverter.isCompatibleWith( dslConverter ) ).isTrue();
		assertThat( dslConverter.isCompatibleWith( compatibleDslConverter ) ).isTrue();
		assertThat( dslConverter.isCompatibleWith( incompatibleDslConverter ) ).isFalse();

		// convert and convertUnknown must behave appropriately on valid input
		assertThat(
				dslConverter.convert( null, toDocumentConvertContext )
		)
				.isNull();
		assertThat(
				dslConverter.convertUnknown( null, toDocumentConvertContext )
		)
				.isNull();
		Iterator<F> fieldValuesIterator = getDocumentFieldValues().iterator();
		for ( V propertyValue : getPropertyValues() ) {
			F fieldValue = fieldValuesIterator.next();
			assertThat(
					dslConverter.convert( propertyValue, toDocumentConvertContext )
			)
					.isEqualTo( fieldValue );
			assertThat(
					dslConverter.convertUnknown( propertyValue, toDocumentConvertContext )
			)
					.isEqualTo( fieldValue );
		}

		// convertUnknown must throw a runtime exception on invalid input
		SubTest.expectException(
				"convertUnknown on invalid input",
				() -> dslConverter.convertUnknown( new Object(), toDocumentConvertContext )
		)
				.assertThrown()
				.isInstanceOf( RuntimeException.class );
	}