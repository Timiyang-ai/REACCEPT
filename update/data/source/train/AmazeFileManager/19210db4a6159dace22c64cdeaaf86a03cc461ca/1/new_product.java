public static String getFileName(String compressedName) {
        compressedName = compressedName.toLowerCase();
        if(isZip(compressedName) || isTar(compressedName) || isRar(compressedName) || compressedName.endsWith(fileExtensionGzipTarShort)) {
            return compressedName.substring(0, compressedName.lastIndexOf("."));
        } else if (isGzippedTar(compressedName)) {
            return compressedName.substring(0,
                    Utils.nthToLastCharIndex(2, compressedName, '.'));
        } else {
            return compressedName;
        }
    }