package com.olga.shoplist.viewlayer.dialogs;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.olga.shoplist.R;
import com.olga.shoplist.ShopListApplication;
import com.olga.shoplist.sharedkernel.types.ProductCategory;
import com.olga.shoplist.viewlayer.activitys.ShopCartListActivity;
import com.olga.shoplist.viewmodel.CardProductViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class NotEmptyShopDialog extends DialogFragment {


    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private CardProductViewModel cardProductViewModel;

    private View v;
    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.not_empty_shop_dialog, container, false);
        getDialog().setCanceledOnTouchOutside(false);
        final ListView listView = v.findViewById(R.id.not_empty_shop_list);
        ((ShopListApplication) this.getActivity().getApplication()).getApplicationComponent()
                .inject(this);
        cardProductViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(CardProductViewModel.class);

        cardProductViewModel.getAvailableShops(ProductCategory.IN_CART).observe(getActivity(), new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> cardProducts) {

                context = getActivity().getApplication().getApplicationContext();
                if (cardProducts == null) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.add("Empty List");
                    getProductList(listView, arrayList, context);
                } else if (cardProducts.size() == 0) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.add("Empty List");
                    getProductList(listView, arrayList, context);
                } else {
                    getProductList(listView, cardProducts, context);
                }

            }
        });

        return v;
    }

    private void getProductList(ListView listView, List<String> list, final Context context) {
        listView.setAdapter(new ArrayAdapter<>(context,
                android.R.layout.simple_list_item_1, list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent = new Intent(context, ShopCartListActivity.class);
                intent.putExtra("SHOP_NAME", ((TextView) view).getText().toString());
                getActivity().startActivity(intent);
                dismiss();
            }
        });
    }
}
