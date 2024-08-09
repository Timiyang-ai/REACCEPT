public byte[] buildClass( ClassDefinition classDef ) throws IOException,
            IntrospectionException,
            SecurityException,
            IllegalArgumentException,
            ClassNotFoundException,
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException,
            NoSuchFieldException {

        ClassWriter cw = new ClassWriter( ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS );
        //ClassVisitor cw = new CheckClassAdapter(cwr);

        this.buildClassHeader( cw,
                classDef );

        // Building fields
        for ( FieldDefinition fieldDef : classDef.getFieldsDefinitions() ) {
            if (! fieldDef.isInherited())
                this.buildField( cw,
                        fieldDef );
        }

        if ( classDef.isTraitable() ) {
            this.buildDynamicPropertyMap( cw, classDef );
            this.buildTraitMap(cw, classDef);
        }

        // Building default constructor
        try {
        this.buildDefaultConstructor( cw,
                classDef );
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Building constructor with all fields
        if (classDef.getFieldsDefinitions().size() > 0) {
            this.buildConstructorWithFields( cw,
                    classDef,
                    classDef.getFieldsDefinitions() );
        }

        // Building constructor with key fields only
        List<FieldDefinition> keys = new LinkedList<FieldDefinition>();
        for ( FieldDefinition fieldDef : classDef.getFieldsDefinitions() ) {
            if ( fieldDef.isKey() ) {
                keys.add( fieldDef );
            }
        }
        if ( !keys.isEmpty() && keys.size() != classDef.getFieldsDefinitions().size() ) {
            this.buildConstructorWithFields( cw,
                    classDef,
                    keys );
        }

        // Building methods
        for ( FieldDefinition fieldDef : classDef.getFieldsDefinitions() ) {
            if (! fieldDef.isInherited()) {
                this.buildGetMethod( cw,
                        classDef,
                        fieldDef );
                this.buildSetMethod( cw,
                        classDef,
                        fieldDef );
            }
        }

        this.buildEquals( cw,
                classDef );
        this.buildHashCode( cw,
                classDef );

        this.buildToString( cw,
                classDef );

        cw.visitEnd();

        byte[] serializedClass = cw.toByteArray();

        return serializedClass;
    }