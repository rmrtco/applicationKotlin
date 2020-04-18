package site.rakshith.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

import site.rakshith.myapplication.OnBoarding;
import site.rakshith.myapplication.R;
import site.rakshith.myapplication.pojo.OnboardItem;

public class OnBoardAdapter extends PagerAdapter {

    Context context;
    ArrayList<OnboardItem> items;


    public OnBoardAdapter(Context context, ArrayList<OnboardItem> items){
        this.context = context;
        this.items = items;
    }


    @Override
    public int getCount() {
        return items.size();
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.onboard_viewpager_layout,container,false);

        OnboardItem item = items.get(position);

        itemView.setBackgroundColor(item.getColor());

        ImageView onbordingImage = itemView.findViewById(R.id.onboardImg);
        onbordingImage.setImageResource(item.getImage());

        TextView header = itemView.findViewById(R.id.descriptionHeader);
        header.setText(item.getHeader());

        TextView description = itemView.findViewById(R.id.descriptionBody);
        description.setText(item.getDescription());

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);
    }
}
