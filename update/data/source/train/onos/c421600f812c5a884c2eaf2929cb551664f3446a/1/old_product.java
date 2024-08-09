@Override
    public void close() throws IOException {

        String className = getYangName();
        className = JavaIdentifierSyntax.getCaptialCase(className);
        String filePath = getFilePath();
        GeneratedFileType fileType = getGeneratedFileTypes();

        /*
         * TODO: add the file header using
         * JavaCodeSnippetGen.getFileHeaderComment
         */

        List<String> imports = new LinkedList<>();

        if (getCachedAttributeList() != null) {
            MethodsGenerator.setAttrInfo(getCachedAttributeList());
            for (AttributeInfo attr : getCachedAttributeList()) {

                if (getImportSet() != null) {
                    imports = new ArrayList<>(getImportSet());
                }
            }
        }

        /**
         * Start generation of files.
         */
        if (fileType.equals(GeneratedFileType.INTERFACE) || fileType.equals(GeneratedFileType.ALL)) {

            /**
             * Create interface file.
             */
            String interfaceFileName = className;
            File interfaceFile = new File(filePath + File.separator + interfaceFileName + JAVA_FILE_EXTENSION);
            interfaceFile = JavaFileGenerator.generateInterfaceFile(interfaceFile, className, imports,
                    getCachedAttributeList(), getPackage());

            /**
             * Create temp builder interface file.
             */
            String builderInterfaceFileName = className + UtilConstants.BUILDER + UtilConstants.INTERFACE;
            File builderInterfaceFile = new File(
                    filePath + File.separator + builderInterfaceFileName + TEMP_FILE_EXTENSION);
            builderInterfaceFile = JavaFileGenerator.generateBuilderInterfaceFile(builderInterfaceFile, className,
                    getPackage(), getCachedAttributeList());

            /**
             * Append builder interface file to interface file and close it.
             */
            JavaFileGenerator.appendFileContents(builderInterfaceFile, interfaceFile);
            JavaFileGenerator.insert(interfaceFile,
                    JavaFileGenerator.closeFile(GeneratedFileType.INTERFACE, interfaceFileName));

            /**
             * Remove temp files.
             */
            JavaFileGenerator.clean(builderInterfaceFile);
        }

        if (fileType.equals(GeneratedFileType.BUILDER_CLASS) || fileType.equals(GeneratedFileType.ALL)) {

            /**
             * Create builder class file.
             */
            String builderFileName = className + UtilConstants.BUILDER;
            File builderFile = new File(filePath + File.separator + builderFileName + JAVA_FILE_EXTENSION);
            MethodsGenerator.setBuilderClassName(className + UtilConstants.BUILDER);

            builderFile = JavaFileGenerator.generateBuilderClassFile(builderFile, className, imports, getPackage(),
                    getCachedAttributeList());

            /**
             * Create temp impl class file.
             */

            String implFileName = className + UtilConstants.IMPL;
            File implTempFile = new File(filePath + File.separator + implFileName + TEMP_FILE_EXTENSION);
            implTempFile = JavaFileGenerator.generateImplClassFile(implTempFile, className, getPackage(),
                    getCachedAttributeList());

            /**
             * Append impl class to builder class and close it.
             */
            JavaFileGenerator.appendFileContents(implTempFile, builderFile);
            JavaFileGenerator.insert(builderFile,
                    JavaFileGenerator.closeFile(GeneratedFileType.BUILDER_CLASS, builderFileName));

            /**
             * Remove temp files.
             */
            JavaFileGenerator.clean(implTempFile);
        }
    }