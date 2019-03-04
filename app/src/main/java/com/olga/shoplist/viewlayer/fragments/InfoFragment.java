package com.olga.shoplist.viewlayer.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olga.shoplist.R;
import com.olga.shoplist.viewlayer.activitys.StartActivity;


public class InfoFragment extends Fragment {

    public InfoFragment() {

    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((StartActivity) getActivity()).getNavigation().setVisibility(View.VISIBLE);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        ((StartActivity) getActivity()).getNavigation().setVisibility(View.VISIBLE);
        System.out.println("Some information");
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
