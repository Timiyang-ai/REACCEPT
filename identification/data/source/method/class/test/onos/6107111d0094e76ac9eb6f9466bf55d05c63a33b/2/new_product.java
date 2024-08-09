public static void addPackageInfo(File path, String classInfo, String pack, boolean isChildNode,
            YangPluginConfig pluginConfig)
            throws IOException {

        pack = parsePkg(pack);

        try {

            File packageInfo = new File(path + SLASH + "package-info.java");
            packageInfo.createNewFile();

            FileWriter fileWriter = new FileWriter(packageInfo);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(CopyrightHeader.getCopyrightHeader());
            bufferedWriter.write(getJavaDoc(PACKAGE_INFO, classInfo, isChildNode, pluginConfig));
            String pkg = PACKAGE + SPACE + pack + SEMI_COLAN;
            if (pkg.length() > LINE_SIZE) {
                pkg = whenDelimiterIsPersent(pkg, LINE_SIZE);
            }
            bufferedWriter.write(pkg);
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new IOException("Exception occured while creating package info file.");
        }
    }