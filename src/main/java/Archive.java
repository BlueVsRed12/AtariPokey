import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Archive {
    private List<Album> albums;

    public Archive() {
        albums = new ArrayList<>();
    }

    public void addSong(Song song) {
        Album album = getAlbum(song.getArtist());
        if (album == null) {
            album = new Album(song.getArtist());
            albums.add(album);
        }

        song.setAlbum(album);
        album.addSong(song);
    }

    public Album getAlbum(String albumName) {
        for (Album album : albums) {
            if (album.getName().equals(albumName)) return album;
        }
        return null;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public int getSongCount() {
        int count = 0;
        for (Album album : albums) {
            count += album.getSongs().size();
        }
        return count;
    }
}
