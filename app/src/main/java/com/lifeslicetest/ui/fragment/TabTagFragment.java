package com.lifeslicetest.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.lifeslicetest.ApplicationController;
import com.lifeslicetest.R;
import com.lifeslicetest.databinding.FragmentTagBinding;
import com.lifeslicetest.service.IClientApi;
import com.lifeslicetest.ui.widget.AbstractTextWatcher;

import javax.inject.Inject;

public class TabTagFragment extends Fragment {

    @Inject
    IClientApi clientApi;

    private FragmentTagBinding fragmentTagBinding;
    private int maxChars;

    public static TabTagFragment newInstance() {
        TabTagFragment fragment = new TabTagFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentTagBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tag, container, false);
        ApplicationController.getComponent().inject(this);

        maxChars = getResources().getInteger(R.integer.max_tag_length);

        fragmentTagBinding.tagInput.addTextChangedListener(new AbstractTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                String query = "";
                if (editable.length() > maxChars) {
                    query = editable.subSequence(0, maxChars).toString();
                    fragmentTagBinding.tagInput.setText(query);
                    fragmentTagBinding.tagInput.setSelection(maxChars);
                }
            }
        });
        fragmentTagBinding.tagInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    goNext();
                    return true;
                }
                return false;
            }
        });

        fragmentTagBinding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNext();
            }
        });
        return fragmentTagBinding.getRoot();
    }

    private void goNext() {
        String tag = fragmentTagBinding.tagInput.getText().toString();
        if (!TextUtils.isEmpty(tag)) {
            fragmentTagBinding.tagInput.getText().clear();
            clientApi.getVideosByTag(tag, 0);
        } else {
            Snackbar.make(fragmentTagBinding.getRoot(),
                    R.string.tag_empty_error,
                    Snackbar.LENGTH_LONG).show();
        }
    }
}
