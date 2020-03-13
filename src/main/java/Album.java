import java.util.ArrayList;
import java.util.List;

public class Album {
    private final String albumName;
    private List<Song> songs;

    public Album(String albumName) {
        this.albumName = albumName;
        songs = new ArrayList<>();
    }

    public String getName() {
        return albumName;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public List<Song> getSongs() {
        return songs;
    }

    public int getSongCount() {
        return songs.size();
    }
}
