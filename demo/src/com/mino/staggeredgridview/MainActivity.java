package com.mino.staggeredgridview;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.StaggeredGridView;
import android.support.v4.widget.StaggeredGridView.LayoutParams;
import android.support.v4.widget.StaggeredGridView.OnScrollListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MainActivity extends Activity
{
	private final static String TAG = MainActivity.class.getSimpleName();
    private StaggeredGridView mSGV;
    private SGVAdapter mAdapter;
    
    private OnScrollListener	mScrollListener = new OnScrollListener() {
    	
		@Override
		public void onScrollStateChanged(ViewGroup view, int scrollState) {
			Log.d(TAG, "[onScrollStateChanged] scrollState:" + scrollState);
			switch (scrollState) {
			case SCROLL_STATE_IDLE:
				setTitle("SCROLL_STATE_IDLE");
				break;
				
			case SCROLL_STATE_FLING:
				setTitle("SCROLL_STATE_FLING");
				break;
				
			case SCROLL_STATE_TOUCH_SCROLL:
				setTitle("SCROLL_STATE_TOUCH_SCROLL");
				break;

			default:
				break;
			}
			
		}

		@Override
		public void onScroll(ViewGroup view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
			Log.d(TAG, "[onScroll] firstVisibleItem:" + firstVisibleItem + " visibleItemCount:"+visibleItemCount + " totalItemCount:" + totalItemCount);
			
		}
	};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new SGVAdapter(this);
        mSGV = (StaggeredGridView) findViewById(R.id.grid);
        mSGV.setColumnCount(4);
        mSGV.setAdapter(mAdapter);
        mSGV.setOnScrollListener(mScrollListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    private final class SGVAdapter extends BaseAdapter
    {

        LayoutInflater mInflater;

        public SGVAdapter(Context ctx)
        {
            mInflater = LayoutInflater.from(ctx);
        }

        @Override
        public int getCount()
        {
            return 30;
        }

        @Override
        public Object getItem(int position)
        {
            return null;
        }

        @Override
        public long getItemId(int position)
        {
            return 0;
        }

        Random r = new Random();

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            final LayoutParams lp;
            final View v;
            switch (position)
            {
                case 0:
                case 29:
                    v = mInflater.inflate(R.layout.element_header, parent, false);
                    lp = new LayoutParams(v.getLayoutParams());
                    lp.span = mSGV.getColumnCount();
                    break;
                case 8:
                case 9:
                case 18:
                case 19:
                    v = mInflater.inflate(R.layout.element_item_small, parent, false);
                    lp = new LayoutParams(v.getLayoutParams());
                    lp.span = 1;
                    break;
                case 10:
                case 20:
                    v = mInflater.inflate(R.layout.element_item_large, parent, false);
                    lp = new LayoutParams(v.getLayoutParams());
                    lp.span = 4;
                    break;
                default:
                    v = mInflater.inflate(R.layout.element_item, parent, false);
                    lp = new LayoutParams(v.getLayoutParams());
                    lp.span = 2;
                    break;
            }
            v.setLayoutParams(lp);
            return v;
        }
    }

}
