package com.a.viewpage.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a.viewpage.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment2 extends Fragment {


    public int index = 0;

    public static PlaceholderFragment2 newInstance(int index) {
        PlaceholderFragment2 fragment = new PlaceholderFragment2();
        fragment.index = index;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("alvin", "fragment:" + index + ",onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("alvin", "fragment:" + index + ",onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("alvin", "fragment:" + index + ",onStop");
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("alvin", "fragment:" + index + ",onActivityCreated");
    }

    private View root;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_view_page2, container, false);
        this.root = root;
        Log.d("alvin", "fragment:" + index + ",onCreateView");

        new Handler().postDelayed(() -> wrapperRecycler(), 200);
        return root;
    }

    private void wrapperRecycler() {
        RecyclerView recycler = root.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(new RecyclerView.Adapter<MyTextViewHolder>() {
            @NonNull
            @Override
            public MyTextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.view_recycler_item, parent, false);
                return new MyTextViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull MyTextViewHolder holder, int position) {
                holder.mytext.updateText("tab:" + index + " item:" + position);
                holder.mytext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getContext(), SubActivity.class));
                    }
                });
            }

            @Override
            public int getItemCount() {
                return 20;
            }
        });
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

    }

    static class MyTextViewHolder extends RecyclerView.ViewHolder {
        public MyTextView mytext;

        public MyTextViewHolder(@NonNull View itemView) {
            super(itemView);
            mytext = itemView.findViewById(R.id.mytext);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("alvin", "fragment:" + index + ",onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("alvin", "fragment:" + index + ",onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("alvin", "fragment:" + index + ",onDestroyView");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d("alvin", "fragment:" + index + ",onHiddenChanged:" + hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("alvin", "setUserVisibleHint isVisibleToUser:" + isVisibleToUser);
    }
}