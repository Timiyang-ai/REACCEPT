private static void writeFile(final File folder, final String file, final String content) {
        if (folder == null || Strings.isNullOrEmpty(file)) {
            return;
        }
        final File vendorDataFile = new File(folder, file);
        try (final FileWriter fw = new FileWriter(vendorDataFile); final BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(content);
        } catch (IOException ex) {
            throw new CloudRuntimeException("Failed to create config drive file " + file, ex);
        }
    }