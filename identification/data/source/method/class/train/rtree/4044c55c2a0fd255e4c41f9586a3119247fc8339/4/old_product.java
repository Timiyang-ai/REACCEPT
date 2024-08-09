public void serialize(RTree<T, S> tree, OutputStream os) throws IOException {
        FlatBufferBuilder builder = new FlatBufferBuilder();
        int n = addNode(tree.root().get(), builder, factory.serializer());
        Rectangle mbb = tree.root().get().geometry().mbr();
        int b = Box_.createBox_(builder, mbb.x1(), mbb.y1(), mbb.x2(), mbb.y2());
        Context_.startContext_(builder);
        Context_.addBounds(builder, b);
        Context_.addMinChildren(builder, tree.context().minChildren());
        Context_.addMaxChildren(builder, tree.context().maxChildren());
        int c = Context_.endContext_(builder);

        int t = Tree_.createTree_(builder, c, n, tree.size());
        Tree_.finishTree_Buffer(builder, t);
        ByteBuffer bb = builder.dataBuffer();
        os.write(bb.array(), bb.position(), bb.remaining());
    }