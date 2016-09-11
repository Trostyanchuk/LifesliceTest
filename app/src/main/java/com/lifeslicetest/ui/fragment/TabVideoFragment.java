package com.lifeslicetest.ui.fragment;

import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;

import com.lifeslicetest.ApplicationController;
import com.lifeslicetest.R;
import com.lifeslicetest.databinding.FragmentVideoBinding;
import com.lifeslicetest.model.Record;
import com.lifeslicetest.service.IBroadcast;
import com.lifeslicetest.service.impl.events.TagVideosEvent;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

public class TabVideoFragment extends Fragment implements RecordsAdapter.OnClickListener {

    @Inject
    IBroadcast broadcast;

    private FragmentVideoBinding fragmentVideoBinding;
    private RecordsAdapter adapter;

    public static TabVideoFragment newInstance() {
        TabVideoFragment fragment = new TabVideoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentVideoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_video, container, false);
        ApplicationController.getComponent().inject(this);

        fragmentVideoBinding.records.setHasFixedSize(true);
        fragmentVideoBinding.records.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentVideoBinding.records.setAdapter(adapter = new RecordsAdapter(getContext(), this));

        final MediaController mediaController = new MediaController(getContext());
        fragmentVideoBinding.mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        mediaController.setAnchorView(fragmentVideoBinding.mediaPlayer);
                        fragmentVideoBinding.mediaPlayer.setMediaController(mediaController);
                    }
                });
            }
        });
        fragmentVideoBinding.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                playVideo(adapter.getNext());
            }
        });

        return fragmentVideoBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        broadcast.register(this);
        fragmentVideoBinding.mediaPlayer.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        broadcast.unregister(this);
        fragmentVideoBinding.mediaPlayer.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentVideoBinding.mediaPlayer.stopPlayback();
    }

    @Subscribe
    public void onTagVideosEvent(TagVideosEvent event) {
        if (TextUtils.isEmpty(event.getErrorMessage())) {
            adapter.setRecords(event.getRecords());
            playVideo(adapter.getNext());
        } else {
            Snackbar.make(fragmentVideoBinding.getRoot(),
                    event.getErrorMessage(),
                    Snackbar.LENGTH_LONG).show();
        }
    }

    private void playVideo(Record record) {

        fragmentVideoBinding.records.scrollToPosition(adapter.getCurrent());

        fragmentVideoBinding.mediaPlayer.stopPlayback();

        if (!TextUtils.isEmpty(record.getVideoLowURL())) {
            fragmentVideoBinding.mediaPlayer.setVideoURI(Uri.parse(record.getVideoLowURL()));
            fragmentVideoBinding.mediaPlayer.start();
        } else {
            //TODO show some dialog
        }
    }

    @Override
    public void onItemClick(Record record) {
        playVideo(record);
    }
}
