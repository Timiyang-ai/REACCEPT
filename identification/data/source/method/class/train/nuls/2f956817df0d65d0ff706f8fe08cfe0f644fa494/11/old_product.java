@Override
    public Result saveBlockHeader(BlockHeaderPo po) {
        if (null == po) {
            return Result.getFailed(KernelErrorCode.NULL_PARAMETER);
        }
        byte[] hashBytes = po.getHash().serialize();
        Result result = dbService.put(ProtocolStorageConstant.DB_AREA_BLOCK_HEADER, hashBytes, po.serialize());
        if (result.isFailed()) {
            return result;
        }
        result = dbService.put(ProtocolStorageConstant.DB_AREA_BLOCK_HEADER_INDEX, new VarInt(po.getHeight()).encode(), hashBytes);
        if (result.isFailed()) {
            this.removeBlockHerader(hashBytes);
            return result;
        }
        dbService.put(ProtocolStorageConstant.DB_AREA_BLOCK_HEADER_INDEX, new VarInt(-1L).encode(), hashBytes);
        return Result.getSuccess();
    }