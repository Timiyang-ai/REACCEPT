@VisibleForTesting
  /* package */ VorbisSetup readSetupHeaders(ParsableByteArray scratch) throws IOException {

    if (vorbisIdHeader == null) {
      vorbisIdHeader = VorbisUtil.readVorbisIdentificationHeader(scratch);
      return null;
    }

    if (commentHeader == null) {
      commentHeader = VorbisUtil.readVorbisCommentHeader(scratch);
      return null;
    }

    // the third packet contains the setup header
    byte[] setupHeaderData = new byte[scratch.limit()];
    // raw data of vorbis setup header has to be passed to decoder as CSD buffer #2
    System.arraycopy(scratch.data, 0, setupHeaderData, 0, scratch.limit());
    // partially decode setup header to get the modes
    Mode[] modes = VorbisUtil.readVorbisModes(scratch, vorbisIdHeader.channels);
    // we need the ilog of modes all the time when extracting, so we compute it once
    int iLogModes = VorbisUtil.iLog(modes.length - 1);

    return new VorbisSetup(vorbisIdHeader, commentHeader, setupHeaderData, modes, iLogModes);
  }