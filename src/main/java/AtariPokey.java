import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.id3.ID3v23Tag;

import java.io.File;

public class AtariPokey {
    private static final String MP3_ROOT = "C:\\Users\\bluevsred12\\Desktop\\ASMA";
//    private static final String ASMA_ROOT = "C:\\Users\\Yossi\\Desktop\\ASMA";
    private static final String SAP_ROOT = "C:\\Users\\bluevsred12\\Desktop\\sap\\asma";

    private Archive archive;

    public AtariPokey() {
        archive = new Archive();

        File root = new File(MP3_ROOT);
        addAllSongs(archive, root);
    }

    public void printArchive() {
        int currentSong = 1;
        int TOTAL_SONGS = archive.getSongCount();

        for (Album album : archive.getAlbums()) {
            updateAlbum(album);
            printAlbum(album, TOTAL_SONGS, currentSong);
            currentSong += album.getSongCount();

            System.out.println();
        }
    }

    public void printAlbum(Album album, int totalSongs, int currentSong) {
        System.out.println("================================");
        System.out.println("\t" + album.getName());
        System.out.println("================================\n");

        for (Song song : album.getSongs()) {
            System.out.println("[" + currentSong + "/" + totalSongs + "]  |  " + song.getTitle());
            currentSong++;
            printSongMeta(song);
            System.out.println();
        }
    }

    public void test1() {
        Album album = archive.getAlbums().get(0);

        int TOTAL_SONGS = album.getSongCount();
        updateAlbum(album);
        printAlbum(album, TOTAL_SONGS, 1);
    }

    public void test2() {
//        Album album = archive.getAlbums().get(0);
//
//        for (Song song : album.getSongs()) {
//            System.out.println(SAPReader.authorNameTester(song.getSapFile()));
//        }
        Song song = archive.getAlbums().get(0).getSongs().get(0);
        System.out.println(SAPReader.authorNameTester(song.getSapFile()));
    }

    public void updateSongMeta(Song song) {
        try {
            AudioFile file = AudioFileIO.read(song.getMp3File());
            file.setTag(new ID3v23Tag());
            file.commit();

            Tag tag = file.getTag();
            tag.setField(FieldKey.ALBUM, song.getAlbumName());
            tag.setField(FieldKey.ARTIST, song.getArtist());
            tag.setField(FieldKey.TITLE, song.getTitle());

            file.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAlbum(Album album) {
        for (Song song : album.getSongs()) {
            updateSongMeta(song);
        }
    }

    public void printSongMeta(Song song) {
        try {
            AudioFile file = AudioFileIO.read(song.getMp3File());
            Tag tag = file.getTag();
            if (tag != null) {
                for (FieldKey fieldKey : FieldKey.values()) {
                    String content = tag.getFirst(fieldKey);
                    if (content.isEmpty()) continue;
                    System.out.println(
                            fieldKey.name().toLowerCase().replaceAll("_", " ") + ": " +
                            content);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void addSong(Archive archive, File mp3File) {
        String pathString = mp3File.toString()
                .replaceAll("_", " ")
                .replaceAll("(\\.mp3)", "");
        String[] divisions = pathString.split("\\\\");
        String title = divisions[divisions.length - 1];
        String albumName = divisions[divisions.length - 2];

        File sapFile = new File(mp3File.toString()
                .replace(MP3_ROOT, SAP_ROOT)
                .replace(".mp3", ".sap"));
        // todo: get this info from inside the .SAP file
        Song song = new Song.SongBuilder(mp3File, sapFile, title, albumName)
                .build();
        archive.addSong(song);
    }

    public static void main(String[] args) {
        new AtariPokey().test2();
    }
}
