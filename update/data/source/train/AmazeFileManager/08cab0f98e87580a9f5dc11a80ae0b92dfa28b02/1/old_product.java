public static String getFileName(String compressedName) {
        compressedName = compressedName.toLowerCase();
        if(isZip(compressedName) || isTar(compressedName) || isRar(compressedName) || is7zip(compressedName)) {
            return compressedName.substring(0, compressedName.lastIndexOf("."));
        } else if (isGzippedTar(compressedName) || isXzippedTar(compressedName) || isLzippedTar(compressedName)) {
            return compressedName.substring(0,
                    Utils.nthToLastCharIndex(2, compressedName, '.'));
        } else {
            return compressedName;
        }
    }