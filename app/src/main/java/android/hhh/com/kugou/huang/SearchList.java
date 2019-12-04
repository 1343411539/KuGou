package android.hhh.com.kugou.huang;

/**
 * Created by Administrator on 2019/12/4.
 */

public class SearchList {
    private String musicName;
    private String writer;

    public SearchList(String musicName, String writer){
        this.musicName = musicName;
        this.writer = writer;
    }

    public String getMusicName() {
        return musicName;
    }

    public String getWriter() {
        return writer;
    }
}
