public static int aggrLength(int pos, int[] treeData) {
        return pos < 0 ? 0 : treeData[pos << 1];
    }