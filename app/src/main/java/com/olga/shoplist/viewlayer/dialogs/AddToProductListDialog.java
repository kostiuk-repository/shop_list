package com.olga.shoplist.viewlayer.dialogs;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.olga.shoplist.R;
import com.olga.shoplist.ShopListApplication;
import com.olga.shoplist.domain.entitys.CardProduct;
import com.olga.shoplist.sharedkernel.types.ProductCategory;
import com.olga.shoplist.viewmodel.CardProductViewModel;

import javax.inject.Inject;

public class AddToProductListDialog extends DialogFragment {


    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private CardProductViewModel cardProductViewModel;

    private static final String ARG_PARAM1 = "label";
    private static final String ARG_PARAM2 = "type";
    private static final String ARG_PARAM3 = "shopName";
    private String label;
    private String type;
    private String shopName;
    private View v;

    public AddToProductListDialog() {

    }

    public static AddToProductListDialog newInstance(String label, String type, String shopName) {
        AddToProductListDialog fragment = new AddToProductListDialog();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, label);
        args.putString(ARG_PARAM2, type);
        args.putString(ARG_PARAM3, shopName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            label = getArguments().getString(ARG_PARAM1);
            type = getArguments().getString(ARG_PARAM2);
            shopName = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        TextView textView = v.findViewById(R.id.top_text);
        textView.setText(type);
        TextView textView2 = v.findViewById(R.id.dialog_product_list_value_type);
        textView2.setText("Wybrales\n" + label + "\nUstaw wartosc : ");
        final EditText editText = v.findViewById(R.id.dialog_product_list_edit_text);
        final Button accept = v.findViewById(R.id.button_accept);
        Button discard = v.findViewById(R.id.button_discard);


        ((ShopListApplication) this.getActivity().getApplication()).getApplicationComponent()
                .inject(this);
        cardProductViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(CardProductViewModel.class);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println(s.length());
                System.out.println(count);
                if (s.length() > 0) {
                    accept.setClickable(true);
                } else if (s.length() == 0) {
                    accept.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editTextValue = editText.getText().toString();
                cardProductViewModel.addCardProduct(
                        new CardProduct(label,
                                type,
                                shopName,
                                Long.valueOf(editTextValue),
                                ProductCategory.IN_CART, "0"));
                dismiss();
            }
        });
        accept.setClickable(false);
        discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.product_custom_dialog, container, false);


        return v;
    }


}
