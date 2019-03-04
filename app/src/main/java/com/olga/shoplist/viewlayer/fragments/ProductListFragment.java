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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.olga.shoplist.R;
import com.olga.shoplist.ShopListApplication;
import com.olga.shoplist.domain.entitys.Product;
import com.olga.shoplist.viewlayer.activitys.StartActivity;
import com.olga.shoplist.viewlayer.dialogs.AddToProductListDialog;
import com.olga.shoplist.viewlayer.fragments.adapters.CustomExpandableListAdapter;
import com.olga.shoplist.viewmodel.ProductViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;


public class ProductListFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    private HashMap<String, List<String>> expandableProducts = new HashMap<>();
    private ProductViewModel productViewModel;

    private ExpandableListView expandableListView;
    private CustomExpandableListAdapter expandableListAdapter;

    private ListView searchableProductList;

    private View view;

    private String mParam1;
    private boolean mKeyboardVisible;


    public ProductListFragment() {

    }


    static ProductListFragment newInstance(String param1) {
        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
        System.out.println("PRODUCT_LIST_FRAGMENT " + mParam1);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shop_list, container, false);
        System.out.println("onCreateView method");
        ((ShopListApplication) this.getActivity().getApplication()).getApplicationComponent()
                .inject(this);
        productViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ProductViewModel.class);

        final EditText editText = view.findViewById(R.id.editText);
        final Context context = getActivity().getApplicationContext();
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (editText.getText().toString().length() > 0) {
                    expandableListView.setVisibility(View.INVISIBLE);
                    searchableProductList.setVisibility(View.VISIBLE);
                    List<String> list = new ArrayList<>();
                    for (Map.Entry<String, List<String>> m :
                            expandableProducts.entrySet()) {
                        for (String str : m.getValue()) {
                            if (str.toLowerCase().startsWith(s.toString()) ||
                                    str.toUpperCase().startsWith(s.toString()) ||
                                    str.startsWith(s.toString())) {
                                list.add(str);
                            }
                        }
                    }

                    searchableProductList.setAdapter(new ArrayAdapter<>(context,
                            android.R.layout.simple_list_item_1, list));
                } else {
                    searchableProductList.setVisibility(View.INVISIBLE);
                    expandableListView.setVisibility(View.VISIBLE);
                }
            }
        });
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    onKeyboardHidden();
                } else {
                    onKeyboardShown();
                }
            }
        });

        searchableProductList = view.findViewById(R.id.searchable_product_list);

        searchableProductList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final AddToProductListDialog[] addToProductListDialog = new AddToProductListDialog[1];
                productViewModel.getProduct(((TextView) view).getText().toString()).observeForever(new Observer<Product>() {
                    @Override
                    public void onChanged(@Nullable Product product) {
                        addToProductListDialog[0] = AddToProductListDialog
                                .newInstance(((TextView) view).getText().toString(), product.getProductUnit(), mParam1);
                        FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
                        final FragmentTransaction transaction = supportFragmentManager.beginTransaction();

                        addToProductListDialog[0].show(transaction, "FRAGMENT_SHOP_DIALOG");
                    }
                });


            }
        });

        productViewModel.getListOfData().observeForever(new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                setData(products);
            }
        });

        return view;
    }

    private void setData(List<Product> product) {

        HashSet<String> set = new HashSet<>();
        for (Product p : product) {
            set.add(p.getProductType());
        }
        for (String key : set) {
            ArrayList<String> productsNames = new ArrayList<>();
            for (Product p : product) {
                if (p.getProductType().equals(key)) {
                    productsNames.add(p.getProductName());
                }
            }
            expandableProducts.put(key, productsNames);
        }

        expandableListView = view.findViewById(R.id.expandable_products_list);
        expandableListAdapter = new CustomExpandableListAdapter(getContext(), new ArrayList<>(expandableProducts.keySet()), expandableProducts);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, final View v, int groupPosition, int childPosition, long id) {
                final AddToProductListDialog[] addToProductListDialog = new AddToProductListDialog[1];
                productViewModel.getProduct(((TextView) (((LinearLayout) v).getChildAt(0))).getText().toString()).observeForever(new Observer<Product>() {
                    @Override
                    public void onChanged(@Nullable Product product) {
                        addToProductListDialog[0] = AddToProductListDialog
                                .newInstance(((TextView) (((LinearLayout) v).getChildAt(0))).getText().toString(), product.getProductUnit(), mParam1);
                        FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
                        final FragmentTransaction transaction = supportFragmentManager.beginTransaction();
                /*if (addToProductListDialog != null) {
                    transaction.remove(addToProductListDialog);
                }*/
                        //transaction.addToBackStack(null);
                        addToProductListDialog[0].show(transaction, "FRAGMENT_SHOP_DIALOG");
                    }
                });

                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void onKeyboardShown() {
        ((StartActivity) getActivity()).getNavigation().setVisibility(View.INVISIBLE);
    }

    public void onKeyboardHidden() {
        ((StartActivity) getActivity()).getNavigation().setVisibility(View.VISIBLE);
    }

}
