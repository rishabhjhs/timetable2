package rishabh.agarwal.holmes.timetable;




import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    String abc="";

    Bitmap bitmap;

    Spinner cv,gv;
    ListView lv, yv, bv;
    List<String> blist;
    List<String> ylist;
    List<String> brlist;
    List<String> dlist;
    List<String> tlist;
    ArrayAdapter<String> adap1;
    ArrayAdapter<String> adap2;
    ArrayAdapter<String> adap3;
    ArrayAdapter<String> adap4;
    ArrayAdapter<String> adap5;
    TextView et1, et2, et3, et4, et5;
    Button b, b1;
    controller pc;
    String s1 = "", s2 = "", s3 = "", s5 = "", s6 = "";
    int i = 0;
    NotificationManager mgr;
    Intent in;
    PendingIntent pin;
    int year, month, day, hh, mm,v;
    AlarmManager am;
    String myyear="",mybranch="",mybatch="",myday="",mytime="";


    String s8="";

    SharedPreferences spd;
    String picturePath="";
    SharedPreferences.Editor speditt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et1 = (TextView) findViewById(R.id.editText);
        cv = (Spinner) findViewById(R.id.editText1);
        gv = (Spinner) findViewById(R.id.editText2);
        et4 = (TextView) findViewById(R.id.editText7);
        et5 = (TextView) findViewById(R.id.editText8);

        b = (Button) findViewById(R.id.button);
        spd = getSharedPreferences("rishabh", MODE_PRIVATE);
        speditt = spd.edit();
        v = R.drawable.b;
        String savedPicturePath = spd.getString("imagepath","");
       // Toast.makeText(MainActivity.this,savedPicturePath, Toast.LENGTH_SHORT).show();
        if(!savedPicturePath.equals(picturePath))
        {
            bitmap = BitmapFactory.decodeFile(savedPicturePath);
            Drawable d = new BitmapDrawable(getResources(),bitmap);
            RelativeLayout bg = (RelativeLayout) findViewById(R.id.abc);
            bg.setBackground(d);
        }
        else
        { speditt.putInt("background", v);
            RelativeLayout bg = (RelativeLayout) findViewById(R.id.abc);
            bg.setBackgroundResource(v);


        }



        mgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        am = (AlarmManager) getSystemService(ALARM_SERVICE);


        pc = new controller(this);

        dlist = new ArrayList<String>();
        dlist = pc.allday();
        adap2 = new ArrayAdapter<String>(MainActivity.this, R.layout.spinner_item, dlist);
        adap2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        cv.setAdapter(adap2);
        tlist = new ArrayList<String>();
        tlist = pc.alltime();
        adap3 = new ArrayAdapter<String>(MainActivity.this,R.layout.spinner_item, tlist);
        adap3.setDropDownViewResource(R.layout.spinner_dropdown_item);
        gv.setAdapter(adap3);




        SharedPreferences sp=	getSharedPreferences("student", MODE_PRIVATE);
        final  SharedPreferences.Editor spedit=sp.edit();
        String myyear= sp.getString("Year", "");

        String mybranch=sp.getString("Branch", "");
        String mybatch=sp.getString("Batch","");
        // String myday=sp.getString("Day","");
        //String mytime=sp.getString("Time","");

        int spinnerValue = sp.getInt("Day", -1);

        int spinnerValue1 = sp.getInt("Time",-1);



        if(myyear.equals("")||mybranch.equals("")||mybatch.equals("")){

        }
        else{
            et4.setText("Year :"+myyear);
            et5.setText("Branch :"+mybranch);
            et1.setText("Batch :"+mybatch);
            // et2.setText("Day :"+myday);
//            et3.setText("Time :"+mytime);
            gv.setSelection(spinnerValue1);
            cv.setSelection(spinnerValue);


            s1=myyear;
            s5=mybranch;
            s6=s5+s1;
            s8=mybatch;
            s2=myday;
            s3=mytime;


        }


        // pc.add();
        // pc.add1();

        et4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
                yv = new ListView(MainActivity.this);
                ylist = new ArrayList<String>();
                ylist = pc.year();
                adap4 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_single_choice, ylist);
                yv.setAdapter(adap4);
                yv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                build.setTitle("Select Your Year");
                build.setView(yv);
                build.setCancelable(true);

                build.setPositiveButton("Select", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id = yv.getCheckedItemPosition();
                        if (id == -1) {
                        } else {
                            String a = ylist.get(id);
                            et4.setText("Year :" + a);
                            s1 =a;


                            Log.e("rishabh", s1);
                        }
                    }
                });
                build.show();
            }
        });
        et5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et4.setText("Select Your Year");
                et1.setText("Select Your Batch");
                //  et2.setText("Select Day");
                // et3.setText("Select The Time");
                AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
                bv = new ListView(MainActivity.this);
                brlist = new ArrayList<String>();
                brlist = pc.allbranch();
                adap5 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_single_choice, brlist);
                bv.setAdapter(adap5);
                bv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                build.setTitle("Select Your Branch");
                build.setView(bv);
                build.setCancelable(true);

                build.setPositiveButton("Select", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id = bv.getCheckedItemPosition();
                        if (id == -1) {
                        } else {

                            String b = brlist.get(id);
                            Log.e("qwe",b);
                            et5.setText("Branch :"+b);
                            s5 = b;
                            Log.e("rishabh", s5);
                        }
                    }
                });
                build.show();
            }
        });
        et1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
                lv = new ListView(MainActivity.this);
                blist = new ArrayList<String>();
                if (et4.getText().toString().equals("") && et5.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Please Select Your Year And Branch", Toast.LENGTH_SHORT).show();
                } else if (!et4.getText().toString().equals("") && et5.getText().toString().equals(""))
                    Toast.makeText(MainActivity.this, "Please Select Branch First", Toast.LENGTH_SHORT).show();
                else if (et4.getText().toString().equals("") && !et5.getText().toString().equals(""))
                    Toast.makeText(MainActivity.this, "Please Select Your Year First ", Toast.LENGTH_SHORT).show();
                else {


                    s6 = s5 + s1;

                    blist = pc.allbatch(s6);
                    adap1 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_single_choice, blist);
                    lv.setAdapter(adap1);
                    lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                    build.setTitle("Select Your Batch");
                    build.setView(lv);
                    build.setCancelable(true);

                    build.setPositiveButton("Select", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int id = lv.getCheckedItemPosition();
                            if (id == -1) {
                            } else {
                                String a = blist.get(id);
                                et1.setText("Batch :"+a);
                                s8 =a;
                                Log.e("rishabh", s8);
                            }
                        }
                    });
                    build.show();
                }
            }
        });
        cv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s2 = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        gv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s3 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("rishabh", s1);
                if (et4.getText().toString().equals("") || et5.getText().toString().equals("") ||et4.getText().toString().equals("Select Your Year")||et1.getText().toString().equals("Select Your Batch"))
                    Toast.makeText(MainActivity.this, "Please Select The Above Missing Entries", Toast.LENGTH_SHORT).show();
                else {
                    int userChoice = gv.getSelectedItemPosition();
                    int userchoice1=cv.getSelectedItemPosition();

                    spedit.putString("Year",s1 );
                    spedit.putString("Branch",s5 );
                    spedit.putString("Batch",s8 );
                    spedit.putInt("Day",userchoice1 );
                    spedit.putInt("Time",userChoice );
                    spedit.commit();



                    abc = pc.ans(s8, s2, s3, s6);
                    AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);





                    if (!abc.equals("Free")&&!abc.equals("")&&!abc.equals("no class")&&!abc.equals("No Class")&&!abc.equals("free")) {
                        build.setTitle("Hey You Have a Class");
                        ;
                        build.setMessage(abc);
                        build.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        build.setNegativeButton("Notify Me", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
                                final View v1 = getLayoutInflater().inflate(R.layout.notify, null);
                                final TextView e1 = (TextView) v1.findViewById(R.id.textView);
                                final TextView e2 = (TextView) v1.findViewById(R.id.textView2);
                                build.setTitle("Set Your Alert Details");
                                build.setView(v1);
                                e1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
                                        build.setTitle("Select Date");
                                        final DatePicker dp = new DatePicker(MainActivity.this);
                                        build.setView(dp);
                                        build.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                day = dp.getDayOfMonth();
                                                month = dp.getMonth() + 1;
                                                year = dp.getYear();
                                                e1.setText(day + "/" + month + "/" + year);

                                            }
                                        });


                                        build.show();


                                    }
                                });
                                e2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
                                        build.setTitle("Select Time");
                                        final TimePicker dp = new TimePicker(MainActivity.this);
                                        build.setView(dp);
                                        build.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                hh = dp.getCurrentHour();
                                                mm = dp.getCurrentMinute();

                                                e2.setText(hh + ":" + mm);

                                            }
                                        });


                                        build.show();


                                    }
                                });

                                AlertDialog.Builder builder = build.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        in = new Intent(MainActivity.this, Myservice.class);
                                        GregorianCalendar gc = new GregorianCalendar(year, month - 1, day, hh, mm);


                                        long stime = gc.getTimeInMillis();
                                        Log.e("rishabh", "" + stime);

                                        Date d = new Date(stime);
                                        Log.e("rishabh", "" + d);
                                        Log.e("rishabh", "" + abc);

                                        in.putExtra("aaaa", abc);
                                        // MainActivity.this.startService(in);

                                        pin = PendingIntent.getService(MainActivity.this, 1, in, PendingIntent.FLAG_UPDATE_CURRENT);
                                        am.set(AlarmManager.RTC, stime, pin);




                                    }
                                });


                                build.show();

                            }
                        });
                    } else {
                        build.setTitle("You Do not Have A Class");
                        build.setMessage(abc);
                        build.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                    }
                    build.show();
                }
            }
        });



    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.About:

                Intent in=new Intent(MainActivity.this,about.class);
                startActivity(in);
                break;
            case R.id.Update:
                if (et4.getText().toString().equals("") || et5.getText().toString().equals("") ||et4.getText().toString().equals("Select Your Year")||et1.getText().toString().equals("Select Your Batch"))
                    Toast.makeText(MainActivity.this, "Please Select The Above Missing Entries", Toast.LENGTH_SHORT).show();
                else {


                    AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
                    build.setTitle("Update Your TimeTable");
                    final View v1 = getLayoutInflater().inflate(R.layout.updatetimetable, null);
                    final TextView e1 = (TextView) v1.findViewById(R.id.editText3);
                    final TextView e2 = (TextView) v1.findViewById(R.id.editText4);
                    final TextView e3 = (TextView) v1.findViewById(R.id.editText5);
                    final EditText e4 = (EditText) v1.findViewById(R.id.editText6);
                    build.setView(v1);
                    e1.setText(s8);
                    e2.setText(s2);
                    e3.setText(s3);
                    build.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String q = e1.getText().toString();
                            String w = e2.getText().toString();
                            String e = e3.getText().toString();
                            String r = e4.getText().toString();
                            Log.e("rishabh", "step505");
                            pc.updateProduct(q, w, e, r, s6);
                            Toast.makeText(MainActivity.this, "Update Succesfull", Toast.LENGTH_SHORT).show();

                        }
                    });
                    build.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });


                    build.show();


                }
                break;
            case R.id.Changeback:
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, 101);
                break;
            case R.id.changedefault:
                speditt.putString("imagepath", "");

                speditt.commit();
                RelativeLayout bg = (RelativeLayout) findViewById(R.id.abc);
                bg.setBackgroundResource(v);

                break;



        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode,    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);

            speditt.putString("imagepath", picturePath);

            speditt.commit();
           // Toast.makeText(MainActivity.this, picturePath, Toast.LENGTH_SHORT).show();
            cursor.close();
            bitmap = BitmapFactory.decodeFile(picturePath);
            Drawable d = new BitmapDrawable(getResources(),bitmap);
            RelativeLayout bg = (RelativeLayout) findViewById(R.id.abc);
            bg.setBackground(d);


        }


    }




}







