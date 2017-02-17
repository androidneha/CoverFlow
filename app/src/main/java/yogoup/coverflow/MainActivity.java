package yogoup.coverflow;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import me.crosswall.lib.coverflow.CoverFlow;
import me.crosswall.lib.coverflow.core.PageItemClickListener;
import me.crosswall.lib.coverflow.core.PagerContainer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PagerContainer container = (PagerContainer) findViewById(R.id.pager_container);
        ViewPager pager = container.getViewPager();
        pager.setAdapter(new MyPagerAdapter());
        pager.setClipChildren(false);
        //
        pager.setOffscreenPageLimit(15);

        container.setPageItemClickListener(new PageItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this,"position:" + position, Toast.LENGTH_SHORT).show();
            }
        });

        boolean showTransformer = getIntent().getBooleanExtra("showTransformer",false);


        if(showTransformer){

            new CoverFlow.Builder()
                    .with(pager)
                    .scale(0.3f)
                    .pagerMargin(getResources().getDimensionPixelSize(R.dimen.pager_margin))
                    .spaceSize(0f)
                    .build();

        }else{
            pager.setPageMargin(30);
        }
    }


    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_cover,null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image_cover);
            imageView.setImageDrawable(getResources().getDrawable(DemoData.covers[position]));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public int getCount() {
            return DemoData.covers.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
    }


}