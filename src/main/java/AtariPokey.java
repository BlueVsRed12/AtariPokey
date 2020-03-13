import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;

import java.io.File;
import java.io.IOException;

public class AtariPokey {
//    private static final String ASMA_ROOT = "C:\\Users\\bluevsred12\\Desktop\\ASMA";
    private static final String ASMA_ROOT = "C:\\Users\\Yossi\\Desktop\\ASMA";

    private Archive archive;

    public AtariPokey() {
        archive = new Archive();

        File root = new File(ASMA_ROOT);
        addAllSongs(archive, root);
    }

    public void printArchive() {
        int currentSong = 0;
        int TOTAL_SONGS = archive.getSongCount();

        for (Album album : archive.getAlbums()) {
            System.out.println("================================");
            System.out.println("\t" + album.getName());
            System.out.println("================================\n");

            for (Song song : album.getSongs()) {
                System.out.println("[" + currentSong + "/" + TOTAL_SONGS + "]" + " /// " + song.getName());
                printSongMeta(song);
                System.out.println();

                currentSong++;
            }

            System.out.println();
        }
    }

    public void test1() {
        Song song = archive.getAlbums().get(0).getSongs().get(0);

        updateSongMeta(song);
        printSongMeta(song);
    }

    public void updateSongMeta(Song song) {

    }

    public void printSongMeta(Song song) {
    }

    public void addAllSongs(Archive archive, File path) {
        if (!path.isDirectory() && path.toString().endsWith(".mp3")) {
            addSong(archive, path);
        } else if (path.isDirectory()) {
            for(File nextPath : path.listFiles()) {
                addAllSongs(archive, nextPath);
            }
        }
    }

    public void addSong(Archive archive, File path) {
        String pathString = path.toString()
                .replaceAll("_", " ")
                .replaceAll("(\\.mp3)", "");
        String[] divisions = pathString.split("\\\\");
        String songName = divisions[divisions.length - 1];
        String albumName = divisions[divisions.length - 2];

        archive.addSong(path, albumName, songName);
    }

    public static void main(String[] args) {
        new AtariPokey().test1();
    }
}
