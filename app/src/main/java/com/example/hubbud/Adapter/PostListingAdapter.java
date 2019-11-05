package com.example.hubbud.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hubbud.Database.FirebaseDatabaseHelper;
import com.example.hubbud.Helper.Prefs;
import com.example.hubbud.MainActivity;
import com.example.hubbud.Model.MessageModel;
import com.example.hubbud.Model.PostModel;
import com.example.hubbud.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PostListingAdapter extends RecyclerView.Adapter<PostListingAdapter.MyViewHolder>{

    private ArrayList<PostModel> blogPostList;
    private Activity mContext;
    private String recievedFrom;


    public PostListingAdapter(Activity context, ArrayList<PostModel> adList) {

        this.mContext = context;
        this.blogPostList = adList;

    }

    public PostListingAdapter(Activity mContext, ArrayList<PostModel> blogPostList, String recievedFrom) {
        this.blogPostList = blogPostList;
        this.mContext = mContext;
        this.recievedFrom = recievedFrom;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        protected TextView title;
        protected TextView description;
        protected TextView date;

        protected RelativeLayout bt_item_detail;


        public MyViewHolder(final View view) {
            super(view);

            title =  (TextView) view.findViewById(R.id.title);
            description =  (TextView) view.findViewById(R.id.description);
            date =  (TextView) view.findViewById(R.id.date);
            bt_item_detail = view.findViewById(R.id.bt_item_detail);

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custome_farm_solutin_layout, null);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_post, null);
        MyViewHolder mh = new MyViewHolder(v);
        return mh;

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {

            //Log.e("TAG", "the array size is: " + blogPostList.size());

            PostModel ad = blogPostList.get(position);


            holder.title.setText(ad.getPostTitle());
            holder.description.setText(ad.getPostDescription());
            holder.date.setText(ad.getPostTime());

            Log.e("TAG", "recievedFrom: "+recievedFrom);

            holder.bt_item_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final Dialog dialog = new Dialog(mContext);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_detail);

                    TextView title = dialog.findViewById(R.id.title);
                    TextView category = dialog.findViewById(R.id.cat);
                    TextView sub_category = dialog.findViewById(R.id.sub);
                    TextView description = dialog.findViewById(R.id.description);
                    TextView date = dialog.findViewById(R.id.date);
                    Button btn_contact = dialog.findViewById(R.id.btn_contact);
                    Button btn_location = dialog.findViewById(R.id.btn_location);
                    final EditText et_message = dialog.findViewById(R.id.et_message);

                    title.setText(blogPostList.get(holder.getAdapterPosition()).getPostTitle());
                    category.setText(blogPostList.get(holder.getAdapterPosition()).getPostCategory());
                    sub_category.setText(blogPostList.get(holder.getAdapterPosition()).getPostSubCategory());
                    description.setText(blogPostList.get(holder.getAdapterPosition()).getPostDescription());
                    date.setText(blogPostList.get(holder.getAdapterPosition()).getPostDate());

                    dialog.show();
                    dialog.setCancelable(true);

                    if (recievedFrom.equals("self")){
                        btn_contact.setVisibility(view.GONE);
                        et_message.setVisibility(View.GONE);
                        btn_location.setVisibility(View.GONE);
                    }

                    btn_contact.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            sendMessage(et_message.getText().toString(),blogPostList.get(holder.getAdapterPosition()));
                            dialog.dismiss();

                        }
                    });

                    btn_location.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Uri gmmIntentUri = Uri.parse("geo:0,0?q=");
                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                            mapIntent.setPackage("com.google.android.apps.maps");
                            mContext.startActivity(mapIntent);

                        }
                    });


                }
            });


        }
    }

    private void sendMessage(String message, PostModel postModel) {

        MessageModel messageModel = new MessageModel(message, Prefs.getUserIDFromPref(mContext),
                postModel.getUserId(),postModel.getPostID(),Calendar.getInstance().getTime().toString());

        new FirebaseDatabaseHelper().sendMessage(messageModel, new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void dataisLoaded(List<PostModel> postsArray, List<String> keys) {

            }

            @Override
            public void dataisInserted() {

                Toast.makeText(mContext, "Message Sent!", Toast.LENGTH_LONG).show();
                Log.e("msg", "Message Sent!");
                mContext.startActivity(new Intent(mContext, MainActivity.class));
                mContext.finish();

            }

            @Override
            public void dataisUpdated() {

            }

            @Override
            public void dataisDeleted() {

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