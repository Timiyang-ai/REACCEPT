public File findFile(String relativePath) throws RedPenException {
        File file;
        if (!secure) {
            file = new File(relativePath);
            if (file.exists()) return file;
        }

        if (base != null) {
            file = new File(base, relativePath);
            if (secureExists(file, base)) return file;
        }

        file = new File(home, relativePath);
        if (secureExists(file, home)) return file;

        throw new RedPenException(relativePath + " is not under " +
          (!secure ? "working directory (" + new File("").getAbsoluteFile() + "), " : "") +
          (base != null ? "base (" + base + "), " : "") +
          "$REDPEN_HOME (" + home.getAbsolutePath() + ").");
    }