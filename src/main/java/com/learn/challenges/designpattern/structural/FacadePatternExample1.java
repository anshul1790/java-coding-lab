package com.learn.challenges.designpattern.structural;

public class FacadePatternExample1 {

    static class VideoConversionLibrary {
        public void initialize() { /* initialization code */ }
        public void loadVideoFile(String filename) { /* load video file code */ }
        public void setOutputFormat(String format) { /* set output format code */ }
        public void convert() { /* video conversion code */ }
        public void save(String outputFilename) { /* save output file code */ }
    }


    // So instead of directly calling the video conversion library
    // we have made the facade that will abstract all the client methods that we need to call
    // to convert the video

    // Hence it is used to serve the purpose of
    // not calling the complex system but provide a facade that will handle all
    // necessary complexity for a client app.
    static class VideoConverterFacade {

        private VideoConversionLibrary library;

        public VideoConverterFacade() {
            library = new VideoConversionLibrary();
            library.initialize();
        }

        public void convertVideo(String inputFilename, String outputFilename, String format) {
            library.loadVideoFile(inputFilename);
            library.setOutputFormat(format);
            library.convert();
            library.save(outputFilename);
        }

    }

    public static void main(String[] args) {

        /*VideoConversionLibrary library = new VideoConversionLibrary();
        library.initialize();
        library.loadVideoFile("video.mp4");
        library.setOutputFormat("avi");
        library.convert();
        library.save("video_converted.avi");*/

        VideoConverterFacade facade = new VideoConverterFacade();
        facade.convertVideo("video.mp4", "video_converted.avi", "avi");
    }

}
