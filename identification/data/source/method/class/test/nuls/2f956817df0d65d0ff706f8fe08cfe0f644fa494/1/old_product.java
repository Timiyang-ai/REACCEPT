@Override
    public Result removeBlockHerader(BlockHeaderPo po) {
        if (null == po || po.getHeight() < 0 || po.getHash() == null) {
            return Result.getFailed(KernelErrorCode.NULL_PARAMETER);
        }
        dbService.delete(ProtocolStorageConstant.DB_AREA_BLOCK_HEADER_INDEX, new VarInt(po.getHeight()).encode());
        dbService.put(ProtocolStorageConstant.DB_AREA_BLOCK_HEADER_INDEX, new VarInt(ProtocolStorageConstant.BEST_BLOCK_HASH_INDEX).encode(), po.getPreHash().serialize());
        return removeBlockHerader(po.getHash().serialize());
    }