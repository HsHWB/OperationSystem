package example.com.operationgsystems;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;

import example.com.adapter.MyWalletBaseAdapter;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private Button button;
    public static Context mainContext;
    private LinearLayout linearLayout1 = null;
    private LinearLayout linearLayout2 = null;
    private TextView spinText = null;
    private TextView timeTurnScheduling = null;
    private Spinner spinner;
    private ListView listView;
    private int screenWidthDip;
    private int screenHeightDip;
    private TimeRound timeRound;
    private DisplayMetrics displayMetrics;
    private MyWalletBaseAdapter baseAdapter;
    private Handler headMoneyHandler;
    private static final String[] spinStr = {"foreground","visible","secondary","hidden","content","empty"};
    private ArrayAdapter<String> spinAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainContext = this.getApplicationContext();

        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidthDip = (int)(displayMetrics.widthPixels);
        screenHeightDip = (int)(displayMetrics.heightPixels);

        mNavigationDrawerFragment = (NavigationDrawerFragment)getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,(DrawerLayout) findViewById(R.id.drawer_layout));

        linearLayout1 = (LinearLayout)this.findViewById(R.id.newThread_linearlayout_gone);
        linearLayout2 = (LinearLayout)this.findViewById(R.id.newThread_linearlayout2_gone);
        listView = (ListView)this.findViewById(R.id.listview);
        timeTurnScheduling = (TextView)this.findViewById(R.id.time_turn_scheduling);
        spinner = (Spinner)this.findViewById(R.id.newThread_linearlayout1_spinner);
        spinText = (TextView)this.findViewById(R.id.newThread_linearlayout1_textview3);

        //将可选内容与ArrayAdapter连接起来
        spinAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinStr);
        //设置下拉列表的风格
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        spinner.setAdapter(spinAdapter);
        //添加事件Spinner事件监听
        spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
        //设置默认值
        spinner.setVisibility(View.VISIBLE);

        AbsListView absListView = (AbsListView)findViewById(R.id.listview);
        double height = screenHeightDip/1.8;
        LinearLayout.LayoutParams listParams = new LinearLayout.LayoutParams(screenWidthDip, (int)height);
        absListView.setLayoutParams(listParams);


    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(position + 1)).commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.GONE);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                if (linearLayout1.getVisibility() == View.VISIBLE) {
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.GONE);
                }
                timeTurnScheduling.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((TextView)MainActivity.this.findViewById(R.id.time_turn_scheduling)).setTextColor(Color.RED);
                        new Thread() {//获取网络线程
                            @Override
                            public void run() {
                                HashMap<Integer, HashMap<String, String>> hashMap = new HashMap<Integer, HashMap<String, String>>();
                                HashMap<String, String> hashMap1 = null;
                                for (int i = 0; i < 6; i++) {
                                    hashMap1 = new HashMap<String, String>();
                                    hashMap1.put("name", spinStr[i]);
                                    hashMap1.put("status", "运行");
                                    hashMap1.put("pri", "10");
                                    hashMap1.put("time", "15");
                                    hashMap1.put("next", "800" + i);
                                    hashMap.put(i, hashMap1);
                                }
                                baseAdapter = new MyWalletBaseAdapter(getApplicationContext(), hashMap, screenWidthDip, screenHeightDip);
                                Message msg = new Message();
                                msg.what = 0x1212;
                                headMoneyHandler.sendMessage(msg);
                            }
                        }.start();
                    }

                });
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                if(linearLayout1.getVisibility() == View.VISIBLE) {
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.GONE);
                }
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                if(linearLayout1.getVisibility() == View.VISIBLE) {
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.GONE);
                }
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                if(linearLayout1.getVisibility() == View.VISIBLE) {
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.GONE);
                }
                break;
        }
        headMoneyHandler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what == 0x1212){
                    listView.setAdapter(baseAdapter);
                }
            }
        };
    }

    //使用数组形式操作
    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
//            spinText.setText("类型："+spinStr[arg2]);
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    class TimeRound extends CountDownTimer {
        public TimeRound(long t1, long t2) {
            super(t1, t2);
        }
        @Override
        public void onFinish() {
            buttonCaptcha.setText("结束");
        }
        @Override
        public void onTick(long finished){
            buttonCaptcha.setText(finished/1000);
        }
    }


    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
