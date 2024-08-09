    @Test
    public void getSortedStreamVideosListTest() {
        List<VideoStream> result = ListHelper.getSortedStreamVideosList(MediaFormat.MPEG_4, true, videoStreamsTestList, videoOnlyStreamsTestList, true);

        List<String> expected = Arrays.asList("144p", "240p", "360p", "480p", "720p", "720p60", "1080p", "1080p60", "1440p60", "2160p", "2160p60");
        //for (VideoStream videoStream : result) System.out.println(videoStream.resolution + " > " + MediaFormat.getSuffixById(videoStream.format) + " > " + videoStream.isVideoOnly);

        assertEquals(result.size(), expected.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(result.get(i).resolution, expected.get(i));
        }

        ////////////////////
        // Reverse Order //
        //////////////////

        result = ListHelper.getSortedStreamVideosList(MediaFormat.MPEG_4, true, videoStreamsTestList, videoOnlyStreamsTestList, false);
        expected = Arrays.asList("2160p60", "2160p", "1440p60", "1080p60", "1080p", "720p60", "720p", "480p", "360p", "240p", "144p");
        assertEquals(result.size(), expected.size());
        for (int i = 0; i < result.size(); i++) assertEquals(result.get(i).resolution, expected.get(i));
    }