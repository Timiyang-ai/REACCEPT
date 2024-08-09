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

        ClassWriter cw = new ClassWriter( ClassWriter.COMPUTE_MAXS );
        //ClassVisitor cw = new CheckClassAdapter(cwr);

        this.buildClassHeader( cw,
                classDef );

        this.buildFields( cw,
                classDef );

        if ( classDef.isTraitable() ) {
            this.buildDynamicPropertyMap( cw, classDef );
            this.buildTraitMap( cw, classDef );
            this.buildFieldTMS( cw, classDef );
        }

        this.buildConstructors( cw,
                classDef );

        this.buildGettersAndSetters( cw,
                classDef );

        this.buildEqualityMethods( cw,
                classDef );

        this.buildToString( cw,
                classDef );

        if ( classDef.isTraitable() ) {
            // must guarantee serialization order when enhancing fields are present
            this.buildSerializationMethods(cw,
                    classDef);
        }

        cw.visitEnd();

        return cw.toByteArray();
    }