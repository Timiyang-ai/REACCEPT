public byte[] buildClass( ClassDefinition classDef, ClassLoader classLoader ) throws IOException,
            IntrospectionException,
            SecurityException,
            IllegalArgumentException,
            ClassNotFoundException,
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException,
            NoSuchFieldException {

        ClassWriter cw = new ClassGenerator.InternalClassWriter( classLoader, ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES );
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