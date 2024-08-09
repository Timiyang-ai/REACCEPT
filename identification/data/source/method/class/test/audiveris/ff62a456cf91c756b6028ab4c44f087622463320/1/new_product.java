public ByteProcessor getBuffer ()
    {
        // Prepare output buffer
        ByteProcessor buffer = new ByteProcessor(width, height);
        buffer.invert();

        switch (orientation) {
        case HORIZONTAL:

            for (int row = 0, size = getSize(); row < size; row++) {
                for (Itr it = new Itr(row); it.hasNext();) {
                    Run run = it.next();

                    for (int c = run.getStart(); c <= run.getStop(); c++) {
                        buffer.set(c, row, 0);
                    }
                }
            }

            break;

        case VERTICAL:

            for (int row = 0, size = getSize(); row < size; row++) {
                for (Itr it = new Itr(row); it.hasNext();) {
                    Run run = it.next();

                    for (int col = run.getStart(); col <= run.getStop(); col++) {
                        buffer.set(row, col, 0);
                    }
                }
            }

            break;
        }

        return buffer;
    }