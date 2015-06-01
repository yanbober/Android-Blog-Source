package com.yanbober.support_library_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author       : yanbo
 * Date         : 2015-06-01
 * Time         : 15:09
 * Description  :
 */
public class ShareFragment extends Fragment {
    private View mParentView;

    private FloatingActionButton mFloatingActionButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mParentView = inflater.inflate(R.layout.share_fragment, container, false);
        return mParentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        demomFloatingActionButton();
    }

    private void demomFloatingActionButton() {
        mFloatingActionButton = (FloatingActionButton) mParentView.findViewById(R.id.action_button);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "结束当前Acitivty", Snackbar.LENGTH_LONG)
                        .setAction("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getActivity().finish();
                            }
                        })
                        .show();
            }
        });
    }
}
