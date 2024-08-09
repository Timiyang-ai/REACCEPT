public Directory getDirectory() throws IOException {
        String fileName = Settings.getString(Settings.KEYS.CPE_INDEX);
        String filePath = Index.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedPath = URLDecoder.decode(filePath, "UTF-8");

        File path = new File(decodedPath + File.separator + fileName);
        path = new File(path.getCanonicalPath());
        Directory dir = FSDirectory.open(path);

        return dir;
    }