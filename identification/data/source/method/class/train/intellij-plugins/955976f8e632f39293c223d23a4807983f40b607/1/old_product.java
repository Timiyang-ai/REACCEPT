public static String unlocalizeFileName(String filename) {
        if (filename.indexOf('_') == -1) {
            return filename;
        }

        String lastToken = filename.substring(filename.lastIndexOf('_') + 1, filename.lastIndexOf('.'));

        // Last token is the language ?
        if (ALL_LANGUAGES.contains(lastToken)) {
            return filename.substring(0, filename.lastIndexOf('_')) + filename.substring(filename.lastIndexOf('.'));
        }

        // Last token is the country ?
        if (ALL_COUNTRIES.contains(lastToken)) {
            String filenameWithoutLastToken = filename.replace("_" + lastToken, "");
            String firstToken = filenameWithoutLastToken.substring(filenameWithoutLastToken.lastIndexOf('_') + 1, filenameWithoutLastToken.lastIndexOf('.'));

            if (ALL_LANGUAGES.contains(firstToken))
                return filenameWithoutLastToken.substring(0, filenameWithoutLastToken.lastIndexOf('_')) + filenameWithoutLastToken.substring(filenameWithoutLastToken.lastIndexOf('.'));
        }

        return filename;
    }