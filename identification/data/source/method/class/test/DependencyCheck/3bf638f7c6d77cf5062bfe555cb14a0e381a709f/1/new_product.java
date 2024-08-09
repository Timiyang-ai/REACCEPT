public static byte[] getChecksum(String algorithm, File file) throws NoSuchAlgorithmException, IOException {
        InputStream fis = null;
        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance(algorithm);
        int numRead;
        try {
            fis = new FileInputStream(file);
            do {
                numRead = fis.read(buffer);
                if (numRead > 0) {
                    complete.update(buffer, 0, numRead);
                }
            } while (numRead != -1);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    Logger.getLogger(Checksum.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return complete.digest();
    }