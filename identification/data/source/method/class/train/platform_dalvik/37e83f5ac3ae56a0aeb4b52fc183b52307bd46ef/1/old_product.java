@Override
    public int read(char[] buf, int offset, int length) throws IOException {
        synchronized (lock) {
            if (!isOpen()) {
                // K0070=InputStreamReader is closed.
                throw new IOException(Msg.getString("K0070")); //$NON-NLS-1$
            }
            // BEGIN android-changed
            // Exception priorities (in case of multiple errors) differ from
            // RI, but are spec-compliant.
            // made implicit null check explicit, used (offset | length) < 0
            // instead of (offset < 0) || (length < 0) to safe one operation
            if (buf == null) {
                throw new NullPointerException(Msg.getString("K0047")); //$NON-NLS-1$
            }
            if ((offset | length) < 0 || offset > buf.length - length) {
                throw new IndexOutOfBoundsException(Msg.getString("K002f")); //$NON-NLS-1$
            }
            // END android-changed
            if (length == 0) {
                return 0;
            }
            
            // allocate enough space for bytes if the default length is
            // inadequate
            int availableLen = in.available();     
            if (Math.min(availableLen, length) > bytes.capacity()) {
                bytes = ByteBuffer.allocate(availableLen);
            }
            
            CharBuffer out = CharBuffer.wrap(buf, offset, length);
            CoderResult result = CoderResult.UNDERFLOW;
            byte[] a = bytes.array();
            boolean has_been_read = false;

            if (!bytes.hasRemaining() || bytes.limit() == bytes.capacity()) {
                // Nothing is available in the buffer...
                if (!bytes.hasRemaining()) {
                    bytes.clear();
                }
                int readed = in.read(a, bytes.arrayOffset(), bytes.remaining());
                if (readed == -1) {
                    endOfInput = true;
                    return -1;
                }
                bytes.limit(readed);
                has_been_read = true;
            }

            while (out.hasRemaining()) {
                if (bytes.hasRemaining()) {
                    result = decoder.decode(bytes, out, false);
                    if (!bytes.hasRemaining() && endOfInput) {
                        decoder.decode(bytes, out, true);
                        decoder.flush(out);
                        decoder.reset();
                        break;
                    }
                    if (!out.hasRemaining()
                            || bytes.position() == bytes.limit()) {
                        bytes.compact();
                    }
                }
                if (in.available() > 0
                        && (!has_been_read && out.hasRemaining())
                        || out.position() == 0) {
                    bytes.compact();
                    int to_read = bytes.remaining();
                    int off = bytes.arrayOffset() + bytes.position();

                    to_read = in.read(a, off, to_read);
                    if (to_read == -1) {
                        if (bytes.hasRemaining()) {
                            bytes.flip();
                        }
                        endOfInput = true;
                        break;
                    }
                    has_been_read = true;
                    if (to_read > 0) {
                        bytes.limit(bytes.position() + to_read);
                        bytes.position(0);
                    }
                } else {
                    break;
                }
            }

            if (result == CoderResult.UNDERFLOW && endOfInput) {
                result = decoder.decode(bytes, out, true);
                // FIXME: should flush at first, but seems ICU has a bug that it
                // will throw IAE if some malform/unmappable bytes found during
                // decoding
                // result = decoder.flush(chars);
                decoder.reset();
            }
            if (result.isMalformed()) {
                throw new MalformedInputException(result.length());
            } else if (result.isUnmappable()) {
                throw new UnmappableCharacterException(result.length());
            }
            if (result == CoderResult.OVERFLOW && bytes.position() != 0) {
                bytes.flip();
            }

            return out.position() - offset == 0 ? -1 : out.position() - offset;
        }
    }