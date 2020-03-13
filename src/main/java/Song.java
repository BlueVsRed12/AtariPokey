import java.io.File;

public class Song {
    private final File mp3File;
    private final File sapFile;

    private Album album;
    private final String title;
    private final String artist;

    public Song(SongBuilder builder) {
        this.mp3File = builder.mp3File;
        this.sapFile = builder.sapFile;
        this.album = builder.album;
        this.title = builder.title;
        this.artist = builder.artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbumName() {
        return album.getName();
    }

    public String getArtist() {
        return artist;
    }

    public File getMp3File() {
        return mp3File;
    }

    public File getSapFile() {
        return sapFile;
    }

    public static class SongBuilder {
        private final File mp3File;
        private final File sapFile;

        private final String artist;
        private Album album;
        private final String title;

        public SongBuilder(File mp3File, File sapFile, String title, String artist) {
            this.mp3File = mp3File;
            this.sapFile = sapFile;
            this.title = title;
            this.artist = artist;
        }

        public void setAlbum(Album album) {
            this.album = album;
        }

        public Song build() {
            return new Song(this);
        }
    }
}
