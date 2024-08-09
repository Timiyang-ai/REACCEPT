public static void addPackageInfo(File path, String classInfo, String pack, boolean isChildNode)
            throws IOException {

        if (pack.contains(ORG)) {
            String[] strArray = pack.split(ORG);
            pack = ORG + strArray[1];
        }
        try {

            File packageInfo = new File(path + SLASH + "package-info.java");
            packageInfo.createNewFile();

            FileWriter fileWriter = new FileWriter(packageInfo);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(CopyrightHeader.getCopyrightHeader());
            bufferedWriter.write(getJavaDoc(PACKAGE_INFO, classInfo, isChildNode));
            bufferedWriter.write(PACKAGE + SPACE + pack + SEMI_COLAN);

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new IOException("Exception occured while creating package info file.");
        }
    }