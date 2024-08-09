  private Path decompress(DecompressorDescriptor.Builder descriptorBuilder) throws Exception {
    descriptorBuilder.setDecompressor(ZipDecompressor.INSTANCE);
    return ZipDecompressor.INSTANCE.decompress(descriptorBuilder.build());
  }