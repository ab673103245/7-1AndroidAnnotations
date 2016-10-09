package qianfeng.a7_1androidannotations;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById  // 或者@ViewById(R.id.iv)，名字与id名一致时，不需要写R.id.iv
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main); // 这里要注释掉


    }

    @Override
    protected void onStart() { // 不能在onCreate中进行注解控件的设值，要在onStart和onResume
        super.onStart();
//        iv.setImageBitmap();
    }

    @Click(R.id.btn)
    public void btnclick()
    {
        download();
    }

    @Background
    public  void download() { // 使用注解修饰符必须是public或者是默认修饰符

        HttpURLConnection con = null;
        try {
            URL url = new URL("http://a1.peoplecdn.cn/f4ba94013a0e63057a7176b317e2327d.jpg");
            con = (HttpURLConnection) url.openConnection();
            Bitmap bitmap = BitmapFactory.decodeStream(con.getInputStream());

            setBitmap2Iv(bitmap);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @UiThread
    public void setBitmap2Iv(Bitmap bitmap) {
        iv.setImageBitmap(bitmap);
    }
}
