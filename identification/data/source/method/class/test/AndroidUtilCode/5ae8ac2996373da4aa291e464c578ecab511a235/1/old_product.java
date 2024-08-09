public static String getFileCharsetSimple(final File file) {
        if (file == null) return "";
        int p = 0;
        InputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(file));
            p = (is.read() << 8) + is.read();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        switch (p) {
            case 0xefbb:
                return "UTF-8";
            case 0xfffe:
                return "Unicode";
            case 0xfeff:
                return "UTF-16BE";
            default:
                try {
                    if (isUtf8(file)) {
                        return "UTF-8";
                    } else {
                        return "GBK";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return "GBK";
                }
        }
    }