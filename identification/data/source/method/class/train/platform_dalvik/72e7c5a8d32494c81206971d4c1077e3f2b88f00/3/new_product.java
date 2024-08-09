@Override
    public int read(char[] buffer, int offset, int length) throws IOException {
        synchronized (lock) {
            if (isClosed()) {
                throw new IOException(Msg.getString("K005b")); //$NON-NLS-1$
            }
            if (offset < 0 || offset > buffer.length - length || length < 0) {
                throw new IndexOutOfBoundsException();
            }
            int outstanding = length;
            while (outstanding > 0) {

                /*
                 * If there are bytes in the buffer, grab those first.
                 */
                int available = end - pos;
                if (available > 0) {
                    int count = available >= outstanding ? outstanding : available;
                    System.arraycopy(buf, pos, buffer, offset, count);
                    pos += count;
                    offset += count;
                    outstanding -= count;
                }

                /*
                 * Before attempting to read from the underlying stream, make
                 * sure we really, really want to. We won't bother if we're
                 * done, or if we've already got some bytes and reading from the
                 * underlying stream would block.
                 */
                if (outstanding == 0 || (outstanding < length && !in.ready())) {
                    break;
                }

                // assert(pos == end);

                /*
                 * If we're unmarked and the requested size is greater than our
                 * buffer, read the bytes directly into the caller's buffer. We
                 * don't read into smaller buffers because that could result in
                 * a many reads.
                 */
                if ((mark == -1 || (pos - mark >= markLimit))
                        && outstanding >= buf.length) {
                    int count = in.read(buffer, offset, outstanding);
                    if (count > 0) {
                        offset += count;
                        outstanding -= count;
                        mark = -1;
                    }

                    break; // assume the source stream gave us all that it could
                }

                if (fillBuf() == -1) {
                    break; // source is exhausted
                }
            }

            int count = length - outstanding;
            return (count > 0 || count == length) ? count : -1;
        }
    }