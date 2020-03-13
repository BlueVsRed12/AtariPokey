import java.io.File;

public class AtariPokey {
    private static final String ASMA_ROOT = "C:\\Users\\bluevsred12\\Desktop\\ASMA";

    private Archive archive;

    public AtariPokey() {
        archive = new Archive();
    }

    public void start() {
        File root = new File(ASMA_ROOT);
        addAllSongs(archive, root);

        for (Album album : archive.getAlbums()) {
            System.out.println("\t" + album.getName());

            for (Song song : album.getSongs()) {
                System.out.println(song.getName());
            }

            System.out.println();
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

    public void addSong(Archive archive, File path) {
        String pathString = path.toString()
                .replaceAll("_", " ")
                .replaceAll("(\\.mp3)", "");
        String[] divisions = pathString.split("\\\\");
        String songName = divisions[divisions.length - 1];
        String albumName = divisions[divisions.length - 2];

        archive.addSong(songName, albumName);
    }

    public static void main(String[] args) {
        new AtariPokey().start();
    }
}
