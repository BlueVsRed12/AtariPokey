import java.io.File;

public class Song {
    private final Album album;
    private final String name;
    private final File file;

    public Song(File file, Album album, String name) {
        this.file = file;
        this.album = album;
        this.name = name;
    }
    public Song(Album album, String name) {
        file = null;
        this.album = album;
        this.name = name;
    }

    public Album getAlbum() {
        return album;
    }

    public String getName() {
        return name;
    }

    public String getAlbumName() {
        return album.getName();
    }
}
