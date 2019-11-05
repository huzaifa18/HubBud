package com.example.hubbud.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hubbud.Helper.Prefs;
import com.example.hubbud.Model.RecentChatModel;
import com.example.hubbud.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecentChatsListingAdapter extends RecyclerView.Adapter<RecentChatsListingAdapter.ViewHolder> {

    protected ItemListener mListener;
    ArrayList<RecentChatModel> mValues;
    Context mContext;

    public RecentChatsListingAdapter(Context context, ArrayList<RecentChatModel> values) {
        mValues = values;
        mContext = context;
    }

    @Override
    public RecentChatsListingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recent_chat_list_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder Vholder, final int position) {
        Vholder.setData(mValues.get(position));
        final RecentChatModel data = mValues.get(position);

        Vholder.recent_tv.setText(data.getLast_text());
        Vholder.name.setText(data.getName());
        Vholder.time_tv.setText(data.getDate());

//        Glide.with(mContext)
//                .load(data.getImg_url())
//                .into(Vholder.imageView);

        Vholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogue(data,position);
                //Toast.makeText(mContext, position + " is clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showDialogue(RecentChatModel data, int position) {

        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_message);

        TextView et_message = dialog.findViewById(R.id.et_message);
        TextView tv_sender = dialog.findViewById(R.id.tv_sender);
        Button btn_contact = dialog.findViewById(R.id.btn_contact);
        Button btn_location = dialog.findViewById(R.id.btn_location);

        et_message.setText(data.getLast_text());
        tv_sender.setText(data.getName());

        dialog.show();
        dialog.setCancelable(true);

        btn_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ Prefs.getPhoneFromPref(mContext)));
                mContext.startActivity(intent);
                dialog.dismiss();

            }
        });

        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //31.5204° N, 74.3587° E
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                mContext.startActivity(mapIntent);
                dialog.dismiss();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public interface ItemListener {
        void onItemClick(RecentChatModel item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name, recent_tv,time_tv;
        CircleImageView profile;
        RecentChatModel item;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            name = (TextView) v.findViewById(R.id.name_tv);
            recent_tv = (TextView) v.findViewById(R.id.recent_tv);
            time_tv = (TextView) v.findViewById(R.id.time_tv);

            profile = v.findViewById(R.id.profile_iv);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, getAdapterPosition() + " is clicked", Toast.LENGTH_SHORT).show();
                }
            });

        }

        public void setData(RecentChatModel item) {
            this.item = item;

            name.setText(item.getName());
            //recent_tv.setText(item.getLast_text());
            time_tv.setText(item.getDate());
            //profile.setImageResource(Integer.parseInt(item.getImage_path()));

        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item);
                Toast.makeText(mContext, getAdapterPosition() + " is clicked", Toast.LENGTH_SHORT).show();
            }
        }
    }
}