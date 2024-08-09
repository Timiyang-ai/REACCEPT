    private static void serialize(RTree<Object, Point> tree, long t, File file, FileOutputStream os,
            Serializer<Object, Point> fbSerializer) throws IOException {
        fbSerializer.write(tree, os);
        os.close();
        System.out.println("written in " + (System.currentTimeMillis() - t) + "ms, " + "file size="
                + file.length() / 1000000.0 + "MB");
        System.out.println("bytes per entry=" + file.length() / tree.size());
    }