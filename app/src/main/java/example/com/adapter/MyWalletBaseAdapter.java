package example.com.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import example.com.operationgsystems.R;

public class MyWalletBaseAdapter extends BaseAdapter{
	
	private HashMap<Integer, HashMap<String, String>> hashMap;
	private ListView listView;
	private Context context;
	private int screenWidth;
	private int screenHeight;
	private int black = 0xff000000;
	
	public MyWalletBaseAdapter(Context context, HashMap<Integer, HashMap<String, String>> hashMap, int screenWidth, int screenHeight){
		super();
		this.context = context;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.hashMap = hashMap;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return hashMap.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		RelativeLayout rlLayout =new RelativeLayout(context);
		AbsListView.LayoutParams params = new AbsListView.LayoutParams(screenWidth, screenHeight/20);
		rlLayout.setLayoutParams(params);

        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView textName = new TextView(context);
        textName.setTextSize(16);
        textName.setId(position*100+1);
        textName.setText(hashMap.get(position).get("name"));
        textName.setTextColor(black);
        lp1.setMargins(25,0,0,0);//左上右下,setPadding是view自身内容和外框的间距,setMargins是View和父容器之间的间距
        rlLayout.addView(textName, lp1);

        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView textStatus = new TextView(context);
        textStatus.setTextSize(16);
        textStatus.setId(position*100+2);
        textStatus.setText(hashMap.get(position).get("status"));
        textStatus.setTextColor(black);
        lp2.setMargins(220,0,0,0);
        rlLayout.addView(textStatus,lp2);

        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView textPri = new TextView(context);
        textPri.setTextSize(16);
        textPri.setId(position*100+3);
        textPri.setText(hashMap.get(position).get("pri"));
        textPri.setTextColor(black);
        lp3.setMargins(380,0,0,0);
        rlLayout.addView(textPri,lp3);

        RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView textTime = new TextView(context);
        textTime.setTextSize(16);
        textTime.setId(position*100+4);
        textTime.setText(hashMap.get(position).get("time"));
        textTime.setTextColor(black);
        lp4.setMargins(500,0,0,0);
        rlLayout.addView(textTime,lp4);

        RelativeLayout.LayoutParams lp5 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView textNext = new TextView(context);
        textNext.setTextSize(16);
        textNext.setId(position*100+5);
        textNext.setText(hashMap.get(position).get("next"));
        textNext.setTextColor(black);
        lp5.setMargins(625,0,0,0);
        rlLayout.addView(textNext,lp5);

		return rlLayout;
	}
	
}