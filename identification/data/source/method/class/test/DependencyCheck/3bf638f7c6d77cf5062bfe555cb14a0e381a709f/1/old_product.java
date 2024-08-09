public static byte[] getChecksum(String algorithm, File file) throws FileNotFoundException, NoSuchAlgorithmException {
        InputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance(algorithm);
        int numRead;
        try {
            do {
                numRead = fis.read(buffer);
                if (numRead > 0) {
                    complete.update(buffer, 0, numRead);
                }
            } while (numRead != -1);
        } catch (IOException ex) {
            Logger.getLogger(Checksum.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Checksum.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return complete.digest();
    }