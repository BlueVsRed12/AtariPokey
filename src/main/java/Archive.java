import java.util.ArrayList;
import java.util.List;

public class Archive {
    private List<Album> albums;

    public Archive() {
        albums = new ArrayList<>();
    }

    public void addSong(String name, String albumName) {
        Album album = getAlbum(albumName);
        if (album == null) {
            album = new Album(albumName);
            albums.add(album);
        }

        album.addSong(new Song(album, name));
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
}
