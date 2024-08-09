@Override
    public RTree<T, S> deserialize(long sizeBytes, InputStream is, InternalStructure structure)
            throws IOException {
        byte[] bytes = readFully(is, (int) sizeBytes);
        Tree_ t = Tree_.getRootAsTree_(ByteBuffer.wrap(bytes));
        Node_ node = t.root();
        Context<T, S> context = new Context<T, S>(t.context().minChildren(),
                t.context().maxChildren(), new SelectorRStar(), new SplitterRStar(), factory);
        final Node<T, S> root;
        if (structure == InternalStructure.SINGLE_ARRAY) {
            root = new NonLeafFlatBuffers<T, S>(node, context, factory.deserializer());
        } else {
            root = toNodeDefault(node, context, factory.deserializer());
        }
        return SerializerHelper.create(Optional.of(root), (int) t.size(), context);
    }