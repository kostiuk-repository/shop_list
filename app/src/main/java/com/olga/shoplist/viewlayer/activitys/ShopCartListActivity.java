package com.olga.shoplist.viewlayer.activitys;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.olga.shoplist.R;
import com.olga.shoplist.ShopListApplication;
import com.olga.shoplist.domain.entitys.CardProduct;
import com.olga.shoplist.sharedkernel.types.ProductCategory;
import com.olga.shoplist.viewlayer.dialogs.ArchiveNameDialog;
import com.olga.shoplist.viewmodel.CardProductViewModel;

import java.util.List;

import javax.inject.Inject;


public class ShopCartListActivity extends BaseActivity {


    private String shopName;
    private RecyclerView recyclerView;
    private ShopCartAdapter shopCartAdapter;
    private LayoutInflater layoutInflater;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private CardProductViewModel cardProductViewModel;
    private List<CardProduct> cardProducts;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value1 = extras.getString("SHOP_NAME");
            if (value1 != null) {
                shopName = value1;
            }
        }
        this.setContentView(R.layout.activity_shop_cart_list);
        recyclerView = findViewById(R.id.shop_list_activity_recycler_view);

        ((ShopListApplication) getApplication()).getApplicationComponent()
                .inject(this);
        cardProductViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(CardProductViewModel.class);
        layoutInflater = getLayoutInflater();
        cardProductViewModel.getCardProductByShopName(shopName, ProductCategory.IN_CART).observeForever(new Observer<List<CardProduct>>() {
            @Override
            public void onChanged(@Nullable List<CardProduct> cardProducts) {
                setListData(cardProducts);
            }
        });

        final Toolbar toolbar = this.findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(this.getResources().getColor(R.color.colorPrimaryDark));
        this.setSupportActionBar(toolbar);

    }

    private void setListData(List<CardProduct> cardProducts) {
        this.cardProducts = cardProducts;

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        shopCartAdapter = new ShopCartAdapter();
        recyclerView.setAdapter(shopCartAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        this.getMenuInflater().inflate(R.menu.list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.arrow:
                final Intent intent = new Intent(this, StartActivity.class);
                this.startActivity(intent);
                return true;
            case R.id.save:
                ArchiveNameDialog archiveNameDialog = ArchiveNameDialog.newInstance(shopName);
                archiveNameDialog.show(getSupportFragmentManager().beginTransaction(), "ARCHIVE_NAME_DIALOG");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private class ShopCartAdapter extends RecyclerView.Adapter<ShopCartAdapter.ShopCartHolder> {

        CardProduct cardProduct;

        @Override
        public ShopCartAdapter.ShopCartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.card_product_list, parent, false);
            return new ShopCartAdapter.ShopCartHolder(v);
        }

        @Override
        public void onBindViewHolder(ShopCartAdapter.ShopCartHolder holder, int position) {

            cardProduct = cardProducts.get(position);
            holder.textView.setText(cardProduct.getProductName());
            holder.textView2.setText(String.valueOf(cardProduct.getProductCount()));
            holder.textView3.setText(cardProduct.getProductType());

        }

        @Override
        public int getItemCount() {
            return cardProducts.size();
        }

        class ShopCartHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private TextView textView;
            private TextView textView2;
            private TextView textView3;


            ShopCartHolder(View itemView) {
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
