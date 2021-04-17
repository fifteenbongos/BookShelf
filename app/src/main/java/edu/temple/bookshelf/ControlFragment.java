package edu.temple.bookshelf;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class ControlFragment extends Fragment {


    Button pauseButton, playButton, stopButton;
    TextView nowPlayingTextView;
    SeekBar seekBar;
    boolean isKilled;
    ControlFragmentInterface parentActivity;

    public ControlFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Fragment needs to communicate with parent activity
        // Verify that activity implemented defined Interface
        if(context instanceof ControlFragment.ControlFragmentInterface) {
            parentActivity = (ControlFragment.ControlFragmentInterface) context;
        } else {
            throw new RuntimeException("Please implement the BookListFragmentInterface");
        }
    }


    public static ControlFragment newInstance() {
        ControlFragment fragment = new ControlFragment();
        Bundle args = new Bundle();


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_control, container, false);

        nowPlayingTextView = v.findViewById(R.id.now_playing);
        pauseButton = v.findViewById(R.id.pause_button);
        playButton = v.findViewById(R.id.play_button);
        stopButton = v.findViewById(R.id.stop_button);
        seekBar = v.findViewById(R.id.seek_bar);

        // Call interface methods on button press
        playButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                parentActivity.bookPlay();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                parentActivity.bookPause();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.bookStop();
            }
        });


        return v;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        isKilled = true;
    }
    // To call methods in ParentActivity
    interface ControlFragmentInterface {
        // Play button
        void bookPlay();
        // Pause button
        void bookPause();
        // Stop button
        void bookStop();
        // Now playing? Maybe just integrate into bookPlay()

        // TODO Seek bar


    }
}