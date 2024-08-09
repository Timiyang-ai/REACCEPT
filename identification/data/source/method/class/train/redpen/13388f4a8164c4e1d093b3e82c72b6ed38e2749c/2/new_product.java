public File findFile(String relativePath) throws RedPenException {
        File file = new File(relativePath);
        if (file.exists()) return file;

        if (base != null) {
            file = new File(base, relativePath);
            if (file.exists()) return file;
        }

        file = new File(home, relativePath);
        if (file.exists()) return file;

        throw new RedPenException(String.format("%s is not under working directory (%s)" + (base != null ? ", base (" + base + ")" : "")  + " or $REDPEN_HOME (%s).",
          relativePath, new File("").getAbsoluteFile(), home.getAbsolutePath()));
    }