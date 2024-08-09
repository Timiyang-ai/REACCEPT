public String getFormattedFileList() {
    StringBuffer formattedFileList = new StringBuffer();
    for (int i = 0; i < this.localFileData.length; i += 2) {
      formattedFileList.append(new String(this.localFileData[i]));
      if (i < this.localFileData.length - 2) {
        formattedFileList.append(", ");
      }
    }
    return formattedFileList.toString();
  }