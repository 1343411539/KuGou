package android.hhh.com.kugou.xiongli.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by vip98 on 2019/11/21.
 */

public class ToastUtils {
    private static Toast toast;
    private static Context context;

    public static void showShort(Context context, CharSequence sequence) {
        if (toast == null) {
            toast = Toast.makeText(context, sequence, Toast.LENGTH_SHORT);
        } else {
            toast.cancel();
            toast = Toast.makeText(context, sequence, Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    public static void showLong(Context context, CharSequence sequence) {
        if (toast == null) {
            toast = Toast.makeText(context, sequence, Toast.LENGTH_LONG);
        } else {
            toast.cancel();
            toast = Toast.makeText(context, sequence, Toast.LENGTH_LONG);
        }
        toast.show();
    }
}
