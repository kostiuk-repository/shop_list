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
import com.olga.shoplist.domain.dao.CardProductDao;
import com.olga.shoplist.domain.entitys.ArchiveProduct;
import com.olga.shoplist.domain.entitys.CardProduct;
import com.olga.shoplist.sharedkernel.types.ProductCategory;
import com.olga.shoplist.viewmodel.ArchiveViewModel;
import com.olga.shoplist.viewmodel.CardProductViewModel;

import java.util.List;

import javax.inject.Inject;


public class ArchiveDetailFragment extends Fragment {


    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private CardProductViewModel archiveViewModel;
    private List<CardProduct> archiveProducts;
    private RecyclerView recyclerView;
    private LayoutInflater layoutInflater;

    public ArchiveDetailFragment() {

    }

    private String archiveId;

    static ArchiveDetailFragment newInstance(String param1) {
        ArchiveDetailFragment fragment = new ArchiveDetailFragment();
        Bundle args = new Bundle();
        args.putString("ARCHIVE_ID", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            archiveId = getArguments().getString("ARCHIVE_ID");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_archive_detail, container, false);
        ((ShopListApplication) getActivity().getApplication()).getApplicationComponent()
                .inject(this);
        archiveViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(CardProductViewModel.class);
        layoutInflater = getLayoutInflater();
        recyclerView = v.findViewById(R.id.archive_detail_fragment_recycler_view);

        archiveViewModel.findCardProductsByArchiveIdAndArchiveType(archiveId, ProductCategory.IN_ARCHIVE).observe(getActivity(), new Observer<List<CardProduct>>() {
            @Override
            public void onChanged(@Nullable List<CardProduct> archiveProducts) {
                setListData(archiveProducts);
            }
        });

        return v;
    }

    private void setListData(List<CardProduct> archiveProducts) {
        this.archiveProducts = archiveProducts;

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        ArchiveCartAdapter archiveCartAdapter = new ArchiveCartAdapter();
        recyclerView.setAdapter(archiveCartAdapter);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private class ArchiveCartAdapter extends RecyclerView.Adapter<ArchiveCartAdapter.ArchiveCartHolder> {

        CardProduct archiveProduct;

        @Override
        public ArchiveCartAdapter.ArchiveCartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.card_product_list, parent, false);
            return new ArchiveCartAdapter.ArchiveCartHolder(v);
        }

        @Override
        public void onBindViewHolder(ArchiveCartAdapter.ArchiveCartHolder holder, int position) {

            archiveProduct = archiveProducts.get(position);
            holder.textView.setText(archiveProduct.getProductName());
            holder.textView2.setText(String.valueOf(archiveProduct.getProductCount()));
            holder.textView3.setText(archiveProduct.getProductType());

        }

        @Override
        public int getItemCount() {
            return archiveProducts.size();
        }

        class ArchiveCartHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private TextView textView;
            private TextView textView2;
            private TextView textView3;


            ArchiveCartHolder(View itemView) {
                super(itemView);
                this.textView = itemView.findViewById(R.id.first_cart_text);
                this.textView2 = itemView.findViewById(R.id.second_cart_text);
                this.textView3 = itemView.findViewById(R.id.third_cart_text);
            }

            @Override
            public void onClick(View v) {

            }

        }

    }

}
