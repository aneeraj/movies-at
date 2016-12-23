package com.netbigs.apps.moviesat;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {


    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        GridView gridView = (GridView)view.findViewById(R.id.gridview);
        gridView.setAdapter(new GridAdapter(getActivity()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i;
                switch (position){

                    case 0:

                        i = new Intent(MovieFragment.this.getActivity(),MovieDetail.class);
                        startActivity(i);
                        break;
                    case 1:
                        i = new Intent(MovieFragment.this.getActivity(),MovieDetail.class);
                        startActivity(i);
                        break;
                    case 2:
                        i = new Intent(MovieFragment.this.getActivity(),MovieDetail.class);
                        startActivity(i);
                        break;
                    case 3:
                        i = new Intent(MovieFragment.this.getActivity(),MovieDetail.class);
                        startActivity(i);
                        break;



                }


            }
        });

        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    @Override
    public void onDetach() {
        super.onDetach();
    }
}
