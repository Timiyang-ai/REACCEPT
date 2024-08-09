public static void writeUnsignedVarInt(int value, DataOutput out) throws IOException {
        byte[] buff = new byte[5];
        int cnt = 0;
        while ((value & 0xFFFFFF80) != 0L) {
            //out.writeByte((value & 0x7F) | 0x80);
            buff[cnt++] = (byte) ((value & 0x7F) | 0x80);
            value >>>= 7;
        }
        //out.writeByte(value & 0x7F);
        buff[cnt++] = (byte) (value & 0x7F);
        out.write(buff, 0, cnt);
    }