package com.olga.shoplist.viewlayer.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.olga.shoplist.R;
import com.olga.shoplist.ShopListApplication;
import com.olga.shoplist.domain.entitys.ShopList;
import com.olga.shoplist.sharedkernel.constants.ShopNames;
import com.olga.shoplist.viewlayer.activitys.StartActivity;
import com.olga.shoplist.viewmodel.ShopListViewModel;

import java.util.List;

import javax.inject.Inject;


public class ShopListFragment extends Fragment {


    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private List<ShopList> shopList;

    private RecyclerView recyclerView;
    private LayoutInflater layoutInflater;

    public static ShopListFragment newInstance() {

        return new ShopListFragment();
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((StartActivity) getActivity()).getNavigation().setVisibility(View.VISIBLE);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shop_list2, container, false);
        recyclerView = v.findViewById(R.id.shop_recycler_view);
        ((StartActivity) getActivity()).getNavigation().setVisibility(View.VISIBLE);
        ((ShopListApplication) this.getActivity().getApplication()).getApplicationComponent()
                .inject(this);
        ShopListViewModel shopListViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ShopListViewModel.class);
        layoutInflater = getActivity().getLayoutInflater();
        shopListViewModel.getProductList().observeForever(new Observer<List<ShopList>>() {
            @Override
            public void onChanged(@Nullable List<ShopList> shopLists) {
                setListData(shopLists);
            }
        });

        return v;
    }


    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void setListData(List<ShopList> shopLists) {
        this.shopList = shopLists;

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        CustomAdapter adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);

    }


    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {//6

        ShopList currentItem;

        @Override
        public CustomAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.shop_list, parent, false);
            return new CustomViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CustomAdapter.CustomViewHolder holder, int position) {

            currentItem = shopList.get(position);
            switch (currentItem.getShopName()) {
                case ShopNames.AUCHAN:
                    holder.imageView.setImageDrawable(
                            getResources().getDrawable(R.drawable.auch)
                    );
                    holder.textView.setText(currentItem.getShopName());
                    break;
                case ShopNames.BIEDRONKA:
                    holder.imageView.setImageDrawable(
                            getResources().getDrawable(R.drawable.biedr)
                    );
                    holder.textView.setText(currentItem.getShopName());
                    break;
                case ShopNames.CARREOUR:
                    holder.imageView.setImageDrawable(
                            getResources().getDrawable(R.drawable.caref)
                    );
                    holder.textView.setText(currentItem.getShopName());
                    break;
                case ShopNames.KAUFLAND:
                    holder.imageView.setImageDrawable(
                            getResources().getDrawable(R.drawable.kaufl)
                    );
                    holder.textView.setText(currentItem.getShopName());
                    break;
                case ShopNames.LIDL:
                    holder.imageView.setImageDrawable(
                            getResources().getDrawable(R.drawable.lidl)
                    );
                    holder.textView.setText(currentItem.getShopName());
                    break;
                case ShopNames.STOKROTKA:
                    holder.imageView.setImageDrawable(
                            getResources().getDrawable(R.drawable.stokrotka)
                    );
                    holder.textView.setText(currentItem.getShopName());
                    break;
                case ShopNames.ZABKA:
                    holder.imageView.setImageDrawable(
                            getResources().getDrawable(R.drawable.zabka)
                    );
                    holder.textView.setText(currentItem.getShopName());
                    break;
                default:
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return shopList.size();
        }

        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private ImageView imageView;
            private TextView textView;
            private ViewGroup container;


            CustomViewHolder(View itemView) {
                super(itemView);
                this.imageView = itemView.findViewById(R.id.shop_image_view);
                this.textView = itemView.findViewById(R.id.shop_name);
                this.container = itemView.findViewById(R.id.shop_item);

                this.container.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {

                ShopList shopListLocal = shopList.get(
                        this.getAdapterPosition()
                );
                FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
                final FragmentTransaction transaction = supportFragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, ProductListFragment.newInstance(shopListLocal.getShopName()), "PRODUCTS_LIST");
                transaction.commit();
            }

        }

    }
}
