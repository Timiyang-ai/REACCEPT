public boolean supportsExtension(String ext) {
        if (ext == null) {
            return false;
        }
        boolean scan = false;
        for (FileTypeAnalyzer a : this.fileTypeAnalyzers) {
            /* note, we can't break early on this loop as the analyzers need to know if
             they have files to work on prior to initialization */
            scan |= a.supportsExtension(ext);
        }
        return scan;
    }