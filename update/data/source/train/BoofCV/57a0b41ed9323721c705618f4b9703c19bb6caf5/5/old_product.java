public static void regionPixelId_to_Compact(ImageSInt32 graph, GrowQueue_I32 segmentId, ImageSInt32 output) {

		InputSanityCheck.checkSameShape(graph,output);

		// Change the label of root nodes to be the new compacted labels
		for( int i = 0; i < segmentId.size; i++ ) {
			graph.data[segmentId.data[i]] = i;
		}

		// In the second pass assign all the children to the new compacted labels
		for( int y = 0; y < output.height; y++ ) {
			int indexGraph = graph.startIndex + y*graph.stride;
			int indexOut = output.startIndex + y*output.stride;
			for( int x = 0; x < output.width; x++ , indexGraph++,indexOut++) {
				output.data[indexOut] = graph.data[graph.data[indexGraph]];
			}
		}
		// need to do some clean up since the above approach doesn't work for the roots
		for( int i = 0; i < segmentId.size; i++ ) {
			int indexGraph = segmentId.data[i] - graph.startIndex;

			int x = indexGraph%graph.stride;
			int y = indexGraph/graph.stride;

			output.data[output.startIndex + y*output.stride + x] = i;
		}
	}