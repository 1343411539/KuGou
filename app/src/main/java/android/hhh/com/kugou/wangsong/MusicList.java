package android.hhh.com.kugou.wangsong;

public class MusicList {
    private String songName;
    private String authorName;
    private String duration;

    public MusicList(String songName,String authorName,String duration){
        this.songName=songName;
        this.authorName=authorName;
        this.duration=duration;
    }

    public String getDuration() {
        return duration;
    }


    public String getSongName() {
        return songName;
    }

    public String getAuthorName() {
        return authorName;
    }


}
