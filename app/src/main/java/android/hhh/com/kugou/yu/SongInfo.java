package android.hhh.com.kugou.yu;

/**
 * Created by Administrator on 2019/11/27 0027.
 */

public class SongInfo {
    private int ID;
    private String songName;
    private String authorName;
    private String backgroundImagePath;
    private String audioFilePath;
    private String albumImagePath;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAlbumImagePath() {
        return albumImagePath;
    }

    public void setAlbumImagePath(String albumImagePath) {
        this.albumImagePath = albumImagePath;
    }

    public String getAudioFilePath() {
        return audioFilePath;
    }

    public void setAudioFilePath(String audioFilePath) {
        this.audioFilePath = audioFilePath;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBackgroundImagePath() {
        return backgroundImagePath;
    }

    public void setBackgroundImagePath(String backgroundImagePath) {
        this.backgroundImagePath = backgroundImagePath;
    }

}
