VorbisSetup readSetupHeaders(ExtractorInput input,  ParsableByteArray scratch)
      throws IOException, InterruptedException {

    if (vorbisIdHeader == null) {
      oggParser.readPacket(input, scratch);
      vorbisIdHeader = VorbisUtil.readVorbisIdentificationHeader(scratch);
      scratch.reset();
    }

    if (commentHeader == null) {
      oggParser.readPacket(input, scratch);
      commentHeader = VorbisUtil.readVorbisCommentHeader(scratch);
      scratch.reset();
    }

    oggParser.readPacket(input, scratch);
    // the third packet contains the setup header
    byte[] setupHeaderData = new byte[scratch.limit()];
    // raw data of vorbis setup header has to be passed to decoder as CSD buffer #2
    System.arraycopy(scratch.data, 0, setupHeaderData, 0, scratch.limit());
    // partially decode setup header to get the modes
    Mode[] modes = VorbisUtil.readVorbisModes(scratch, vorbisIdHeader.channels);
    // we need the ilog of modes all the time when extracting, so we compute it once
    int iLogModes = VorbisUtil.iLog(modes.length - 1);
    scratch.reset();

    return new VorbisSetup(vorbisIdHeader, commentHeader, setupHeaderData, modes, iLogModes);
  }