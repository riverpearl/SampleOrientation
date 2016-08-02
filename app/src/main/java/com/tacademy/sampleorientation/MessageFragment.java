package com.tacademy.sampleorientation;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {

    private static final String ARG_MESSAGE = "param1";
    private String mMessage;

    public MessageFragment() {
        // Required empty public constructor
    }

    public static MessageFragment newInstance(String message) {
        MessageFragment mf = new MessageFragment();
        Bundle b = new Bundle();
        b.putString(ARG_MESSAGE, message);
        mf.setArguments(b);
        return mf;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
            mMessage = getArguments().getString(ARG_MESSAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        TextView tv = (TextView)view.findViewById(R.id.text_message);
        tv.setText(mMessage);
        return view;
    }

}
