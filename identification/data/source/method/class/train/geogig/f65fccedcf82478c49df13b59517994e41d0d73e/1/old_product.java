public static void writeUnsignedVarLong(long value, DataOutput out) throws IOException {
        byte[] buff = new byte[10];
        int cnt = 0;
        while ((value & 0xFFFFFFFFFFFFFF80L) != 0L) {
            //out.writeByte(((int) value & 0x7F) | 0x80);
            buff[cnt++] = (byte) (((int) value & 0x7F) | 0x80);
            value >>>= 7;
        }
        //out.writeByte((int) value & 0x7F);
        buff[cnt++] = (byte) ((int) value & 0x7F);
        out.write(buff, 0, cnt);
    }