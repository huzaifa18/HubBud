package com.example.hubbud;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hubbud.Adapter.RecentChatsListingAdapter;
import com.example.hubbud.Database.FirebaseDatabaseHelper;
import com.example.hubbud.Helper.Prefs;
import com.example.hubbud.Model.MessageModel;
import com.example.hubbud.Model.RecentChatModel;
import com.example.hubbud.Model.Users;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends Fragment {

    RecyclerView rv_main;
    RecentChatsListingAdapter chatAdapter;
    RecentChatModel chatModel;
    LinearLayoutManager layoutManager;
    ArrayList<RecentChatModel> list;

    SwipeRefreshLayout swipe_layout;

    View view;

    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_message, container, false);


        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setVisibility(View.GONE);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();



            }
        });

        initialize();
        return view;
    }

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initialize() {

        rv_main = (RecyclerView) view.findViewById(R.id.rv_main);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_main.setLayoutManager(layoutManager);
        list = new ArrayList<>();

        swipe_layout = view.findViewById(R.id.swipe_layout_msg);

        /*for (int i = 0; i<10; i++) {

            list.add(new RecentChatModel("Image Path", "Name", "Text", "19/04/2019"));

        }*/

        chatAdapter = new RecentChatsListingAdapter(getContext(), list);
        rv_main.setAdapter(chatAdapter);

        refreshListener();
        readAllUsers();

    }

    private void refreshListener() {

        swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list.clear();

                readAllUsers();
            }
        });

    }

    private void readAllUsers() {
        swipe_layout.setRefreshing(true);
        new FirebaseDatabaseHelper().readAllUsers(new FirebaseDatabaseHelper.UserStatus() {
            @Override
            public void userisLoaded(List<Users> usersArray, List<String> keys) {

                for (int i = 0; i < usersArray.size(); i++) {

                    Log.e("user", "userMail: " + usersArray.get(i).getEmail());

                    getAllMessages(usersArray.get(i).getUserid(), usersArray.get(i).getUsername());

                }
            }

            @Override
            public void userisInserted() {

            }

            @Override
            public void userisUpdated() {

            }

            @Override
            public void userisDeleted() {

            }
        });

    }

    private void getAllMessages(final String id, final String name) {

        new FirebaseDatabaseHelper().getAllMessages(id, new FirebaseDatabaseHelper.MessageStatus() {
            @Override
            public void userisLoaded(List<MessageModel> messagesArray, List<String> keys) {
                swipe_layout.setRefreshing(false);
                Toast.makeText(getContext(), "Messages Recieved!", Toast.LENGTH_LONG).show();
                Log.e("msg", "Messages Size: " + messagesArray.size());

                for (int i = 0; i < messagesArray.size(); i++) {

                    Log.e("id", "id: " + Prefs.getUserIDFromPref(getContext()));
                    Log.e("id", "sender_id: " + messagesArray.get(i).getSender_id());
                    Log.e("id", "reciever_id: " + messagesArray.get(i).getReciever_id());

                    if (Prefs.getUserIDFromPref(getContext()).equals(messagesArray.get(i).getSender_id()) || Prefs.getUserIDFromPref(getContext()).equals(messagesArray.get(i).getReciever_id())) {

                        list.add(new RecentChatModel("Photo", name, messagesArray.get(i).getMessage(), messagesArray.get(i).getTime()));

                    }

                }

                chatAdapter.notifyDataSetChanged();
            }

            @Override
            public void userisInserted() {

            }

            @Override
            public void userisUpdated() {

            }

            @Override
            public void userisDeleted() {

            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
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

}
