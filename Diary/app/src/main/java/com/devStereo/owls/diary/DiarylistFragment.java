package com.devStereo.owls.diary;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.devStereo.owls.R;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DiarylistFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DiarylistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiarylistFragment extends Fragment implements AdapterView.OnItemClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<Item> list;
    ItemListAdapter adapter= null;
    View view ;
    ListView listView;
    private OnFragmentInteractionListener mListener;
    OnHeadlineSelectedListener mCallback;
    public DiarylistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiarylistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiarylistFragment newInstance(String param1, String param2) {
        DiarylistFragment fragment = new DiarylistFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      view=  inflater.inflate(R.layout.fragment_diarylist, container, false);
        listView = (ListView)view.findViewById(R.id.listView);
        list = new ArrayList<>();
        adapter= new ItemListAdapter(getContext(),R.layout.items,list);
        listView.setAdapter(adapter);

        Cursor cursor =MainActivity.sqLiteHelper.getData("SELECT * FROM MEMO");
        list.clear();
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String topic = cursor.getString(1);
            String contents =cursor.getString(2);
            list.add(new Item(id,topic,contents));
        }
        adapter.notifyDataSetChanged();
        // Inflate the layout for this fragment

        listView.setOnItemClickListener(this);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int tid =list.get(position).getId();
        String topic= list.get(position).getTopic();
        String contents= list.get(position).getContents();

        ((MainActivity)MainActivity.mContext).bundleManager.setBundle(tid,topic,contents);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().hide(DiarylistFragment.this).commit();
       ((MainActivity)MainActivity.mContext).goFix(new View(getApplicationContext()));
       // Toast.makeText(getContext(),"Tid: " +tid+" topic: "+topic+" contents: "+contents,Toast.LENGTH_LONG).show();
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

    public interface OnHeadlineSelectedListener {
        public void goFix(View v);
    }


}
