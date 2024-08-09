    private static void deserialize(InternalStructure structure, File file,
            Serializer<Object, Point> fbSerializer, boolean backpressure) throws Exception {
        long t = System.currentTimeMillis();
        InputStream is = new FileInputStream(file);
        t = System.currentTimeMillis();
        RTree<Object, Point> tr = fbSerializer.read(is, file.length(), structure);
        System.out.println(tr.root().get());

        System.out.println("read in " + (System.currentTimeMillis() - t) + "ms");
        Observable<Entry<Object, Point>> o = tr.search(Geometries.rectangle(40, 27.0, 40.5, 27.5));
        if (backpressure)
            o = o.take(10000);
        int found = o.count().toBlocking().single();
        System.out.println("found=" + found);
        assertEquals(22, found);
        System.out.println(tr.size());
    }