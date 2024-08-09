static void writeFile(File folder, String file, String content) {
        try {
            FileUtils.write(new File(folder, file), content, com.cloud.utils.StringUtils.getPreferredCharset(), false);
        } catch (IOException ex) {
            throw new CloudRuntimeException("Failed to create config drive file " + file, ex);
        }
    }