package android.hhh.com.kugou.yu;

import android.icu.text.MessagePattern;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2019/11/28 0028.
 */

public class SongInfoService {
    public static List<SongInfo> getInfosFromXML(InputStream is)throws Exception{
        return null;

    }
    public static List<SongInfo> getInfosFromJson(InputStream is)throws IOException{
        byte[] buffer=new byte[is.available()];
        is.read(buffer);
        String json=new String(buffer,"utf-8");
        //使用Gson库解析JSON数据
        Gson gson=new Gson();
        Type listType=new TypeToken<List<SongInfo>>(){}.getType();
        List<SongInfo> songInfos=gson.fromJson(json,listType);
        return songInfos;
    }
}
