public ByteBuf getBufferFor(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= capacity()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index
                    + " - Bytes needed: " + index + ", maximum is "
                    + capacity());
        }

        //Return the component byte buffer
        return components[componentId(index)];

    }