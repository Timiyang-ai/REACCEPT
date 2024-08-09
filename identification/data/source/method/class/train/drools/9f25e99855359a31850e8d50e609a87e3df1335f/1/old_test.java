@Test
    public void testBuildClass() {
        try {
            ClassBuilder builder = new ClassBuilder();

            ClassDefinition classDef = new ClassDefinition( "org.drools.TestClass1",
                                                            null,
                                                            new String[]{"java.io.Serializable"} );
            FieldDefinition intDef = new FieldDefinition( "intAttr",
                                                          "int" );

            FieldDefinition stringDef = new FieldDefinition( "stringAttr",
                                                             "java.lang.String" );//"java.lang.String" );
            classDef.addField( intDef );
            classDef.addField( stringDef );
            
            Class clazz = build(builder, classDef);

            
            
            intDef.setReadWriteAccessor( store.getAccessor( clazz,
                                                            intDef.getName(),
                                                            classLoader ) );
            stringDef.setReadWriteAccessor( store.getAccessor( clazz,
                                                               stringDef.getName(),
                                                               classLoader ) );

            byte[] d = builder.buildClass( classDef );

            assertSame( "Returned class should be the same",
                               clazz,
                               classDef.getDefinedClass() );
            assertEquals( "Class name should be equal",
                                 classDef.getClassName(),
                                 clazz.getName() );

            Serializable instance = (Serializable) clazz.newInstance();

            String stringValue = "Atributo String ok";
            stringDef.setValue( instance,
                                stringValue );
            assertEquals( "Attribute should have been correctly set",
                                 stringValue,
                                 stringDef.getValue( instance ) );

            int intValue = 50;
            intDef.setValue( instance,
                             new Integer( intValue ) );
            assertEquals( "Attribute should have been correctly set",
                                 intValue,
                                 ((Integer) intDef.getValue( instance )).intValue() );

            // testing class rebuilding
            clazz = build(builder, classDef);

        } catch ( Exception e ) {
            e.printStackTrace();
            fail( "Error creating class" );
        }
    }