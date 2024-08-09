@Override
    public Result removeBlockHerader(BlockHeaderPo po) {
        if (null == po || po.getHeight() < 0 || po.getHash() == null || po.getPreHash() == null) {
            return Result.getFailed(KernelErrorCode.NULL_PARAMETER);
        }
        dbService.delete(ProtocolStorageConstant.DB_NAME_BLOCK_HEADER_INDEX, new VarInt(po.getHeight()).encode());
        try {
            dbService.put(ProtocolStorageConstant.DB_NAME_BLOCK_HEADER_INDEX, BEST_BLOCK_KEY, po.getPreHash().serialize());
        } catch (IOException e) {
            Log.error(e);
        }
        try {
            return removeBlockHerader(po.getHash().serialize());
        } catch (IOException e) {
            Log.error(e);
            return Result.getFailed();
        }
    }