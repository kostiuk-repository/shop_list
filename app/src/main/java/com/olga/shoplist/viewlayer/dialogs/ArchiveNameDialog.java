package com.olga.shoplist.viewlayer.dialogs;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.db.SimpleSQLiteQuery;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.olga.shoplist.R;
import com.olga.shoplist.ShopListApplication;
import com.olga.shoplist.domain.entitys.Archive;
import com.olga.shoplist.domain.entitys.CardProduct;
import com.olga.shoplist.sharedkernel.types.ProductCategory;
import com.olga.shoplist.viewlayer.activitys.StartActivity;
import com.olga.shoplist.viewmodel.ArchiveViewModel;
import com.olga.shoplist.viewmodel.CardProductViewModel;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.inject.Inject;

public class ArchiveNameDialog extends DialogFragment {

    private View v;
    private String shopName;

    public static ArchiveNameDialog newInstance(String param1) {
        ArchiveNameDialog fragment = new ArchiveNameDialog();
        Bundle args = new Bundle();
        args.putString("SHOP_NAME_FOR_ARCHIVE_NAME_DIALOG", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            shopName = getArguments().getString("SHOP_NAME_FOR_ARCHIVE_NAME_DIALOG");
        }
    }

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private CardProductViewModel cardProductViewModel;
    private ArchiveViewModel archiveViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.archive_name_dialog, container, false);
        ((ShopListApplication) getActivity().getApplication()).getApplicationComponent()
                .inject(this);
        cardProductViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(CardProductViewModel.class);
        archiveViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ArchiveViewModel.class);
        final EditText editText = v.findViewById(R.id.archive_name_edit_text);
        Button accept = v.findViewById(R.id.archive_name_button_accept);
        Button discard = v.findViewById(R.id.archive_name_button_discard);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Archive archive = new Archive(editText.getText().toString(), new Date(), shopName);
                Toast.makeText(getActivity(), "Saved to archive!",
                        Toast.LENGTH_LONG).show();
                cardProductViewModel.getCardProductByShopName(shopName,ProductCategory.IN_CART).observeForever(new Observer<List<CardProduct>>() {
                    @Override
                    public void onChanged(@Nullable List<CardProduct> cardProducts) {

                        archiveViewModel.addArchiveToDatabase(archive);
                        System.out.println(cardProducts);
                        for (int i = 0; i < cardProducts.size(); i++) {
                            CardProduct cardProduct = cardProducts.get(i);
                            cardProduct.setArchiveType(ProductCategory.IN_ARCHIVE);
                            cardProduct.setArchiveId(archive.getArchiveId());
                            cardProductViewModel.updateCardProduct(cardProduct);
                        }

                    }
                });
                final Intent intent2 = new Intent(getActivity(), StartActivity.class);
                getActivity().startActivity(intent2);
                dismiss();
            }
        });
        discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return v;
    }
}
