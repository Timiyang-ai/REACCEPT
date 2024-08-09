public static byte[] encryptMD5File(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            FileChannel channel = fis.getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(buffer);
            return md.digest();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        } finally {
            FileUtils.closeIO(fis);
        }
        return null;
    }