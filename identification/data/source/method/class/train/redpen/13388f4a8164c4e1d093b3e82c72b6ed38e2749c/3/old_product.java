public File findFile(String relativePath) throws RedPenException {
        File file = new File(relativePath);
        if (file.exists()) return file;
        file = new File(home, relativePath);
        if (file.exists()) return file;
        throw new RedPenException(String.format("%s is not under $REDPEN_HOME(%s) or current directory(%s).",
          relativePath, home.getAbsolutePath(), new File("").getAbsoluteFile()));
    }