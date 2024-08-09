public static void unzipFile(String zipFilePath, String destDirPath)
            throws IOException {
        if (!FileUtils.createOrExistsDir(destDirPath)) return;
        ZipFile zf = new ZipFile(zipFilePath);
        Enumeration<?> entries = zf.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String str = destDirPath + File.separator + entry.getName();
            File desFile = new File(str);
            if (entry.isDirectory()) {
                if (!FileUtils.createOrExistsDir(desFile)) return;
            } else {
                if (!FileUtils.createOrExistsFile(desFile)) return;
                InputStream in = null;
                OutputStream out = null;
                try {
                    in = zf.getInputStream(entry);
                    out = new FileOutputStream(desFile);
                    byte buffer[] = new byte[MB];
                    int len;
                    while ((len = in.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                } finally {
                    FileUtils.closeIO(in, out);
                }
            }
        }
    }