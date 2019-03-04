package com.olga.shoplist.viewlayer.fragments;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olga.shoplist.R;
import com.olga.shoplist.viewmodel.CardProductViewModel;

import javax.inject.Inject;


public class CardFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private CardProductViewModel shopListViewModel;

    public CardFragment() {

    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_shop, container, false);
    }

    public void onButtonPressed(final Uri uri) {

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
