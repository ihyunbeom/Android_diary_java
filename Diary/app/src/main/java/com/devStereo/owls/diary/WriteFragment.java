package com.devStereo.owls.diary;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.devStereo.owls.R;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WriteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WriteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText topic ;// Topic of write frag
    private  EditText contents;//Contents of write frag
    private ImageButton submit;// Submit memo to inner db by using DB manager;
//    public static DBManager dbManager = new DBManager(getApplicationContext(), "DIARY.db", null, 1);

    private OnFragmentInteractionListener mListener;

    public WriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WriteFragment newInstance(String param1, String param2) {
        WriteFragment fragment = new WriteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getApplicationContext(),"onCreate",Toast.LENGTH_SHORT);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Toast.makeText(getApplicationContext(),"onCreateView",Toast.LENGTH_LONG);
        Log.i("","now in  sonCreateView");
        final View view = inflater.inflate(R.layout.fragment_write,container, false);

        topic = (EditText) view.findViewById(R.id.topic);
        topic.setText("hello world");
        contents  = (EditText) view.findViewById(R.id.contents);
        submit = (ImageButton)view.findViewById(R.id.submit);

        Log.i("","now in  onCreateView");

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("","onClickClick");
                String mytopic =topic.getText().toString();
                String myContents = contents.getText().toString();
                MainActivity.sqLiteHelper.insertData(mytopic,myContents);
               // dbManager.insert("INSERT INTO DIARY VALUES(null,'"+mytopic+"','"+myContents+"');");
                Toast.makeText(getApplicationContext(),"write Success",Toast.LENGTH_SHORT);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().hide(WriteFragment.this).commit();
              //  MainActivity mainActivity = new MainActivity();
                ((MainActivity)MainActivity.mContext).createStar(new View(getApplicationContext()));
            }
        });
//        init(inflater);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    /*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    /*
    * RedOwl- > Write Back-end start
    * */


//    private void init(LayoutInflater inflater){
//
////        LayoutInflater layoutInflater = (LayoutInflater)((MainActivity)getActivity()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.fragment_write,null);
//
//
//        topic = (EditText) view.findViewById(R.id.topic);
//        contents  = (EditText) view.findViewById(R.id.contents);
//        submit = (ImageButton)view.findViewById(R.id.submit);
//
//
//
//        submit.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Log.i("","onClickClick");
//                String mytopic =topic.getText().toString();
//                String myContents = topic.getText().toString();
//                dbManager.insert("INSERT INTO MEMO VALUES(null,'"+mytopic+"','"+myContents+"',datetime());");
//                Toast.makeText(getApplicationContext(),"write Success",Toast.LENGTH_SHORT);
//
//            }
//        });
//
//    }





}
