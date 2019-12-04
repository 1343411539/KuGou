package android.hhh.com.kugou.xiongli.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by vip98 on 2019/11/28.
 */

public class CopyContentUtil {
    private ClipboardManager mClipboard;
    private ClipData mClip;
    private Context context;
    private TextView view;
    private TextView text;

    public CopyContentUtil(Context context, TextView view, TextView text) {
        this.context = context;
        this.view = view;
        this.text = text;
    }

    public void init() {
        mClipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        //设置需要复制的文本内容
        String str;
        str = text.getText().toString();
        mClip = ClipData.newPlainText("str", str);
        //将文本内容放到系统的剪切板里面去
        mClipboard.setPrimaryClip(mClip);
        if (view != null) {
            ToastUtils.showShort(context, view.getText() + "已复制到粘贴板");
        }else{
            ToastUtils.showShort(context, str + "已复制到粘贴板");
        }
    }
}
