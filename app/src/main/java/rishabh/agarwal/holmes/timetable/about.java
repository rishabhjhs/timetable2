package rishabh.agarwal.holmes.timetable;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Holmes on 1/4/2016.
 */
public class about extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutss);
        tv1=(TextView)findViewById(R.id.textView3);
        tv2=(TextView)findViewById(R.id.textView4);
        tv3=(TextView)findViewById(R.id.textView5);
        tv4= (TextView)findViewById(R.id.textView6);
        tv5=(TextView)findViewById(R.id.textView7);
        tv6=(TextView)findViewById(R.id.textView8);
        tv7=(TextView)findViewById(R.id.textView9);

       /* tv1.setText("Timetable");
        tv2.setText("Version 2.0");
        tv3.setText("Supported Colleges");
        tv4.setText("JUET");
        tv5.setText("Developed By");
        tv6.setText("Rishabh Agarwal");
        tv7.setText("2013-2017");*/

    }
}
