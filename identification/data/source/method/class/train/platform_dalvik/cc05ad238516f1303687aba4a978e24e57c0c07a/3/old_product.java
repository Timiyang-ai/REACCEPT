public static void main(String[] args) {
        ByteBuffer directBuf = ByteBuffer.allocateDirect(64);
        //ByteBuffer directBuf = ByteBuffer.allocateDirect(65);

        ShortBuffer shortBuf = directBuf.asShortBuffer();

        short[] myShorts = {
            1000, 1001, 1002, 1003, 1004, 1005, 1006, 1007,
            1008, 1009, 1010, 1011, 1012, 1013, 1014, 1015,
            1016, 1017, 1018, 1019, 1020, 1021, 1022, 1023,
            1024, 1025, 1026, 1027, 1028, 1029, 1030, 1031
        };

        shortBuf.position(0);
        shortBuf.put(myShorts, 0, 32);      // should work
        shortBuf.position(0);
        shortBuf.put(myShorts, 16, 16);     // should work
        shortBuf.put(myShorts, 16, 16);     // advance to end

        try {
            shortBuf.put(myShorts, 0, 1);     // should fail
            System.err.println("ERROR: out-of-bounds put succeeded\n");
        } catch (BufferOverflowException boe) {
            System.out.println("Got expected buffer overflow exception");
        }

        try {
            shortBuf.position(0);
            shortBuf.put(myShorts, 0, 33);     // should fail
            System.err.println("ERROR: out-of-bounds put succeeded\n");
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Got expected out-of-bounds exception");
        }

        try {
            shortBuf.position(16);
            shortBuf.put(myShorts, 0, 17);     // should fail
            System.err.println("ERROR: out-of-bounds put succeeded\n");
        } catch (BufferOverflowException boe) {
            System.out.println("Got expected buffer overflow exception");
        }
    }