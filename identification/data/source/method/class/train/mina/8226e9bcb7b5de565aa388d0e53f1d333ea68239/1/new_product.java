public IoBuffer clear() {
        position = 0;
        mark = UNSET_MARK;

        BufferNode node = buffers.head;
        int offset = 0;

        while (node != null) {
            node.buffer.clear();
            node.offset = offset;
            offset += node.buffer.limit();
            node = node.next;
        }

        limit = offset;
        buffers.length = 0;
        buffers.current = buffers.head;

        return this;
    }