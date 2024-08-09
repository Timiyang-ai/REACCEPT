    public void removeBlockHerader() {
        service.removeBlockHerader(entity);
        BlockHeaderPo po = this.service.getBlockHeaderPo(entity.getHash());
        assertNull(po);
    }