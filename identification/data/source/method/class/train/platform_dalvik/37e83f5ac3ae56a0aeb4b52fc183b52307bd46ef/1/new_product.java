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

            CharBuffer out = CharBuffer.wrap(buf, offset, length);
            CoderResult result = CoderResult.UNDERFLOW;

            // bytes.remaining() indicates number of bytes in buffer
            // when 1-st time entered, it'll be equal to zero
            boolean needInput = !bytes.hasRemaining();

            while (out.hasRemaining()) {
                // fill the buffer if needed
                if (needInput) {
                    if ((in.available() == 0) && (out.position() > offset)) {
                        // we could return the result without blocking read
                        break;
                    }

                    int to_read = bytes.capacity() - bytes.limit();
                    int off = bytes.arrayOffset() + bytes.limit();
                    int was_red = in.read(bytes.array(), off, to_read);

                    if (was_red == -1) {
                        endOfInput = true;
                        break;
                    } else if (was_red == 0) {
                        break;
                    }
                    bytes.limit(bytes.limit() + was_red);
                    needInput = false;
                }

                // decode bytes
                result = decoder.decode(bytes, out, false);

                if (result.isUnderflow()) {
                    // compact the buffer if no space left
                    if (bytes.limit() == bytes.capacity()) {
                        bytes.compact();
                        bytes.limit(bytes.position());
                        bytes.position(0);
                    }
                    needInput = true;
                } else {
                    break;
                }
            }

            if (result == CoderResult.UNDERFLOW && endOfInput) {
                result = decoder.decode(bytes, out, true);
                // FIXME: should flush at first, but seems ICU has a bug that it
                // will throw IAE if some malform/unmappable bytes found during
                // decoding
                // result = decoder.flush(out);
                decoder.reset();
            }
            if (result.isMalformed()) {
                throw new MalformedInputException(result.length());
            } else if (result.isUnmappable()) {
                throw new UnmappableCharacterException(result.length());
            }

            return out.position() - offset == 0 ? -1 : out.position() - offset;
        }
    }