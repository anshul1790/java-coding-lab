package com.learn.challenges.designpattern.structural;

/*
    https://refactoring.guru/design-patterns/adapter
    Allows incompatible interfaces to work together
 */
public class AdapterPatternExample1 {

    // Adaptee class
    static class Mp3Player {

        void playMp3File(String fileName) {
            System.out.println("Play MP3 file");
        }
    }

    /*
        Now to make this Mp3 player also play the wav files, either we
        need to write convertor inside the mp3player class;
        Or write adapter to convert the file and then let mp3 player play it
     */
    static interface AudioPlayerAdapter {
        void playSong(String file);
    }

    static class WavMp3Adapter implements AudioPlayerAdapter {

        Mp3Player mp3Player;

        public WavMp3Adapter(Mp3Player mp3Player) {
            this.mp3Player = mp3Player;
        }

        @Override
        public void playSong(String file) {
            covertAudioToMp3(file);
            mp3Player.playMp3File(file);
        }

        String covertAudioToMp3(String file) {
            System.out.println(file + " is converted to mp3");
            return null;
        }
    }

    public static void main(String[] args) {
        Mp3Player mp3Player = new Mp3Player();
        //mp3Player.playMp3File("hello.mp3");

        AudioPlayerAdapter player = new WavMp3Adapter(mp3Player);
        player.playSong("hello.wav");

    }

}
