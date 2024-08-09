private static boolean isUtf8(File file) throws Exception {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        // 读取第一个字节
        int code = bis.read();
        do {
            BitSet bitSet = convert2BitSet(code);
            if (bitSet.get(0)) {
                // 多字节时，再读取N个字节
                if (!checkMultiByte(bis, bitSet)) {
                    bis.close();
                    return false;
                }
            }
            // 单字节时什么都不用做，再次读取字节
            code = bis.read();
        } while (code != -1);
        bis.close();
        return true;
    }