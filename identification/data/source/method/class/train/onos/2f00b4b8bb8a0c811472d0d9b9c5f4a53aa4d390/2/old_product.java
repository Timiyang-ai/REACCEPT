@Override
    public void close() throws IOException {

        flushCacheAttrToTempFile();

        String className = getYangName();
        className = JavaIdentifierSyntax.getCaptialCase(className);
        String path = getRelativeFilePath();
        int fileType = getGeneratedFileTypes();

        /*
         * TODO: add the file header using
         * JavaCodeSnippetGen.getFileHeaderComment
         */

        List<String> imports = new LinkedList<>();
        String importString;

        for (ImportInfo importInfo : getImportSet()) {
            importString = "";
            if (importInfo.getPkgInfo() != null) {
                importString = importString + importInfo.getPkgInfo() + ".";
            }
            importString = importString + importInfo.getClassInfo();
            imports.add(importString);
        }

        /**
         * Start generation of files.
         */
        if ((fileType & GeneratedFileType.INTERFACE_MASK) != 0
                || fileType == GeneratedFileType.GENERATE_INTERFACE_WITH_BUILDER) {

            /**
             * Create interface file.
             */
            String interfaceFileName = className;
            File interfaceFile = JavaFileGenerator.getFileObject(path, interfaceFileName, JAVA_FILE_EXTENSION);
            interfaceFile = JavaFileGenerator.generateInterfaceFile(interfaceFile, className, imports,
                    getCachedAttributeList(), path.replace('/', '.'));

            /**
             * Create temp builder interface file.
             */
            String builderInterfaceFileName = className + UtilConstants.BUILDER + UtilConstants.INTERFACE;
            File builderInterfaceFile = JavaFileGenerator.getFileObject(path, builderInterfaceFileName,
                    TEMP_FILE_EXTENSION);
            builderInterfaceFile = JavaFileGenerator.generateBuilderInterfaceFile(builderInterfaceFile, className,
                    path.replace('/', '.'), getCachedAttributeList());

            /**
             * Append builder interface file to interface file and close it.
             */
            JavaFileGenerator.appendFileContents(builderInterfaceFile, interfaceFile);
            JavaFileGenerator.insert(interfaceFile,
                    JavaFileGenerator.closeFile(GeneratedFileType.INTERFACE_MASK, interfaceFileName));

            /**
             * Remove temp files.
             */
            JavaFileGenerator.clean(builderInterfaceFile);
        }

        if ((fileType & GeneratedFileType.BUILDER_CLASS_MASK) != 0
                || fileType == GeneratedFileType.GENERATE_INTERFACE_WITH_BUILDER) {

            /**
             * Create builder class file.
             */
            String builderFileName = className + UtilConstants.BUILDER;
            File builderFile = JavaFileGenerator.getFileObject(path, builderFileName, JAVA_FILE_EXTENSION);
            builderFile = JavaFileGenerator.generateBuilderClassFile(builderFile, className, imports,
                    path.replace('/', '.'), getCachedAttributeList());

            /**
             * Create temp impl class file.
             */

            String implFileName = className + UtilConstants.IMPL;
            File implTempFile = JavaFileGenerator.getFileObject(path, implFileName, TEMP_FILE_EXTENSION);
            implTempFile = JavaFileGenerator.generateImplClassFile(implTempFile, className,
                    path.replace('/', '.'), getCachedAttributeList());

            /**
             * Append impl class to builder class and close it.
             */
            JavaFileGenerator.appendFileContents(implTempFile, builderFile);
            JavaFileGenerator.insert(builderFile,
                    JavaFileGenerator.closeFile(GeneratedFileType.BUILDER_CLASS_MASK, builderFileName));

            /**
             * Remove temp files.
             */
            JavaFileGenerator.clean(implTempFile);
        }
    }