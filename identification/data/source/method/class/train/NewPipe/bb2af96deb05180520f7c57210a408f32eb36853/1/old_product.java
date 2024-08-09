public static List<VideoStream> getSortedStreamVideosList(MediaFormat defaultFormat, boolean showHigherResolutions, List<VideoStream> videoStreams, List<VideoStream> videoOnlyStreams, boolean ascendingOrder) {
        ArrayList<VideoStream> retList = new ArrayList<>();
        HashMap<String, VideoStream> hashMap = new HashMap<>();

        if (videoOnlyStreams != null) {
            for (VideoStream stream : videoOnlyStreams) {
                if (!showHigherResolutions && HIGH_RESOLUTION_LIST.contains(stream.getResolution())) continue;
                retList.add(stream);
            }
        }
        if (videoStreams != null) {
            for (VideoStream stream : videoStreams) {
                if (!showHigherResolutions && HIGH_RESOLUTION_LIST.contains(stream.getResolution())) continue;
                retList.add(stream);
            }
        }

        // Add all to the hashmap
        for (VideoStream videoStream : retList) hashMap.put(videoStream.getResolution(), videoStream);

        // Override the values when the key == resolution, with the defaultFormat
        for (VideoStream videoStream : retList) {
            if (videoStream.getFormat() == defaultFormat) hashMap.put(videoStream.getResolution(), videoStream);
        }

        retList.clear();
        retList.addAll(hashMap.values());
        sortStreamList(retList, ascendingOrder);
        return retList;
    }