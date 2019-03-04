package com.olga.shoplist.viewlayer.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.olga.shoplist.R;
import com.olga.shoplist.ShopListApplication;
import com.olga.shoplist.domain.entitys.Archive;
import com.olga.shoplist.viewlayer.activitys.StartActivity;
import com.olga.shoplist.viewmodel.ArchiveViewModel;

import java.util.List;

import javax.inject.Inject;


public class ArchiveFragment extends Fragment {

    private RecyclerView recyclerView;
    private View v;
    private LayoutInflater layoutInflater;
    private ArchiveAdapter archiveAdapter;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ArchiveViewModel archiveViewModel;
    private List<Archive> archives;

    public ArchiveFragment() {

    }


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Didi I here?");

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_archive, container, false);
        ((StartActivity) getActivity()).getNavigation().setVisibility(View.VISIBLE);

        ((ShopListApplication) getActivity().getApplication()).getApplicationComponent()
                .inject(this);
        archiveViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ArchiveViewModel.class);

        archiveViewModel.getArchives().observe(getActivity(), new Observer<List<Archive>>() {
            @Override
            public void onChanged(@Nullable List<Archive> archives) {
                setListData(archives);
            }
        });

        layoutInflater = getLayoutInflater();
        recyclerView = v.findViewById(R.id.archive_recycler_view);
        recyclerView.setAdapter(new ArchiveAdapter());
        return v;
    }

    private void setListData(List<Archive> archives) {
        this.archives = archives;

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        archiveAdapter = new ArchiveAdapter();
        recyclerView.setAdapter(archiveAdapter);
        recyclerView.setClickable(true);

    }

    private class ArchiveAdapter extends RecyclerView.Adapter<ArchiveAdapter.ArchiveHolder> {

        Archive archive;

        @Override
        public ArchiveAdapter.ArchiveHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.cart_layout, parent, false);
            v.setClickable(true);
            return new ArchiveAdapter.ArchiveHolder(v);
        }

        @Override
        public void onBindViewHolder(ArchiveAdapter.ArchiveHolder holder, int position) {

            archive = archives.get(position);
            holder.textView.setText(archive.getArchiveName());
            holder.textView2.setText(archive.getShopName());
            holder.textView3.setText(archive.getDate().toString());

        }

        @Override
        public int getItemCount() {
            return archives.size();
        }

        class ArchiveHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private TextView textView;
            private TextView textView2;
            private TextView textView3;
            private ViewGroup container;


            ArchiveHolder(View itemView) {
                super(itemView);
                this.textView = itemView.findViewById(R.id.card_text_archive_name);
                this.textView2 = itemView.findViewById(R.id.card_shop_name);
                this.textView3 = itemView.findViewById(R.id.card_date);
                this.container = itemView.findViewById(R.id.card_archive);
                this.container.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                ArchiveDetailFragment archiveDetailFragment = (ArchiveDetailFragment) getActivity().getSupportFragmentManager().findFragmentByTag("ArchiveDetailFragment");
                if (archiveDetailFragment == null) {
                    archiveDetailFragment = ArchiveDetailFragment.newInstance(archives.get(this.getAdapterPosition()).getArchiveId());
                }
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, archiveDetailFragment, "ArchiveDetailFragment").commit();
            }

        }

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
