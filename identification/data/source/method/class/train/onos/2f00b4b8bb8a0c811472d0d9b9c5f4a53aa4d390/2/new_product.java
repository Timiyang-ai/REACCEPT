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

        List<String> imports = new ArrayList<>();
        String importString;

        for (ImportInfo importInfo : new ArrayList<ImportInfo>(getImportSet())) {
            importString = UtilConstants.IMPORT;
            if (importInfo.getPkgInfo() != null) {
                importString = importString + importInfo.getPkgInfo() + ".";
            }
            importString = importString + importInfo.getClassInfo() + UtilConstants.SEMI_COLAN + UtilConstants.NEW_LINE;
            imports.add(importString);
        }
        java.util.Collections.sort(imports);

        /**
         * Start generation of files.
         */
        if ((fileType & GeneratedFileType.INTERFACE_MASK) != 0
                || fileType == GeneratedFileType.GENERATE_INTERFACE_WITH_BUILDER) {

            /**
             * Create interface file.
             */
            String interfaceFileName = className;
            File interfaceFile = JavaFileGenerator.getFileObject(path, interfaceFileName, JAVA_FILE_EXTENSION, this);
            interfaceFile = JavaFileGenerator.generateInterfaceFile(interfaceFile, className, imports,
                    getCachedAttributeList(), path.replace('/', '.'), this);
            /**
             * Create temp builder interface file.
             */
            String builderInterfaceFileName = className + UtilConstants.BUILDER + UtilConstants.INTERFACE;
            File builderInterfaceFile = JavaFileGenerator.getFileObject(path, builderInterfaceFileName,
                    TEMP_FILE_EXTENSION, this);
            builderInterfaceFile = JavaFileGenerator.generateBuilderInterfaceFile(builderInterfaceFile, className,
                    path.replace('/', '.'), getCachedAttributeList(), this);
            /**
             * Append builder interface file to interface file and close it.
             */
            JavaFileGenerator.appendFileContents(builderInterfaceFile, interfaceFile);
            JavaFileGenerator.insert(interfaceFile,
                    JavaFileGenerator.closeFile(GeneratedFileType.INTERFACE_MASK, interfaceFileName));
            /**
             * Close file handle for interface files.
             */
            JavaFileGenerator.closeFileHandles(builderInterfaceFile);
            JavaFileGenerator.closeFileHandles(interfaceFile);

            /**
             * Remove temp files.
             */
            JavaFileGenerator.clean(builderInterfaceFile);
        }

        imports.add(UtilConstants.MORE_OBJECT_IMPORT);
        imports.add(UtilConstants.JAVA_UTIL_OBJECTS_IMPORT);
        java.util.Collections.sort(imports);

        if ((fileType & GeneratedFileType.BUILDER_CLASS_MASK) != 0
                || fileType == GeneratedFileType.GENERATE_INTERFACE_WITH_BUILDER) {

            /**
             * Create builder class file.
             */
            String builderFileName = className + UtilConstants.BUILDER;
            File builderFile = JavaFileGenerator.getFileObject(path, builderFileName, JAVA_FILE_EXTENSION, this);
            builderFile = JavaFileGenerator.generateBuilderClassFile(builderFile, className, imports,
                    path.replace('/', '.'), getCachedAttributeList(), this);
            /**
             * Create temp impl class file.
             */

            String implFileName = className + UtilConstants.IMPL;
            File implTempFile = JavaFileGenerator.getFileObject(path, implFileName, TEMP_FILE_EXTENSION, this);
            implTempFile = JavaFileGenerator.generateImplClassFile(implTempFile, className,
                    path.replace('/', '.'), getCachedAttributeList(), this);
            /**
             * Append impl class to builder class and close it.
             */
            JavaFileGenerator.appendFileContents(implTempFile, builderFile);
            JavaFileGenerator.insert(builderFile,
                    JavaFileGenerator.closeFile(GeneratedFileType.BUILDER_CLASS_MASK, builderFileName));

            /**
             * Close file handle for classes files.
             */
            JavaFileGenerator.closeFileHandles(implTempFile);
            JavaFileGenerator.closeFileHandles(builderFile);

            /**
             * Remove temp files.
             */
            JavaFileGenerator.clean(implTempFile);
        }

        if ((fileType & GeneratedFileType.GENERATE_TYPEDEF_CLASS) != 0) {

            /**
             * Create builder class file.
             */
            String typeDefFileName = className;
            File typeDefFile = JavaFileGenerator.getFileObject(path, typeDefFileName, JAVA_FILE_EXTENSION, this);
            typeDefFile = JavaFileGenerator.generateTypeDefClassFile(typeDefFile, className, imports,
                    path.replace('/', '.'), getCachedAttributeList(), this);
            JavaFileGenerator.insert(typeDefFile,
                    JavaFileGenerator.closeFile(GeneratedFileType.GENERATE_TYPEDEF_CLASS, typeDefFileName));

            /**
             * Close file handle for classes files.
             */
            JavaFileGenerator.closeFileHandles(typeDefFile);
        }

        closeTempDataFileHandles(className, getCodeGenFilePath() + getRelativeFilePath());
        JavaFileGenerator
                .cleanTempFiles(new File(getCodeGenFilePath() + getRelativeFilePath() + File.separator + className
                        + TEMP_FOLDER_NAME_SUFIX));
    }