package com.example.hubbud.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.example.hubbud.Helper.Prefs;
import com.example.hubbud.MainActivity;
import com.example.hubbud.Model.DataIds;
import com.example.hubbud.PostsListing;
import com.example.hubbud.R;

import java.util.ArrayList;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder> {

    private ArrayList<DataIds> blogPostList;
    private Activity mContext;
    private String recievedFrom;
    private String title;


    public SubCategoryAdapter(Activity context, ArrayList<DataIds> adList) {

        this.mContext = context;
        this.blogPostList = adList;

    }

    public SubCategoryAdapter(Activity mContext, ArrayList<DataIds> blogPostList, String recievedFrom, String title) {
        this.blogPostList = blogPostList;
        this.mContext = mContext;
        this.recievedFrom = recievedFrom;
        this.title = title;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        protected TextView title;

        public MyViewHolder(final View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.tv);

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custome_farm_solutin_layout, null);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_category_listing, null);
        MyViewHolder mh = new MyViewHolder(v);
        return mh;

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        //Log.e("TAG", "the array size is: " + blogPostList.size());

        final DataIds ad = blogPostList.get(position);

        holder.title.setText(ad.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (recievedFrom.equals("dashboard")) {

                    Intent intent = new Intent(mContext,PostsListing.class);
                    intent.putExtra("category", title);
                    intent.putExtra("sub_category", ad.getName());
                    mContext.startActivity(intent);

                } else {

                    if (title.equals("Art & Crafts")) {

                        Prefs.addingHobby1(mContext, title, ad.getName());
                        Log.e("hob","Hobby Title: " + Prefs.getHobby1FromPref(mContext));
                        Log.e("hob","Hobby Sub Category: " + Prefs.getHobby1subFromPref(mContext));

                    } else if (title.equals("Sports")){

                        Prefs.addingHobby2(mContext, title, ad.getName());
                        Log.e("hob","Hobby Title: " + Prefs.getHobby2FromPref(mContext));
                        Log.e("hob","Hobby Sub Category: " + Prefs.getHobby2subFromPref(mContext));

                    } else if (title.equals("Tourism")){

                        Prefs.addingHobby3(mContext, title, ad.getName());
                        Log.e("hob","Hobby Title: " + Prefs.getHobby3FromPref(mContext));
                        Log.e("hob","Hobby Sub Category: " + Prefs.getHobby3subFromPref(mContext));

                    }

                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.putExtra("hobby", title);
                    mContext.startActivity(intent);

                }

            }
        });

    }


    @Override
    public int getItemCount() {

        return blogPostList.size();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }


    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        setFadeAnimation(holder.itemView);
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(2000);
        view.startAnimation(anim);
    }

}