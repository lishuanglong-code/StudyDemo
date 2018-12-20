package study.demo.activity.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import study.demo.App;
import study.demo.MainActivity;
import study.demo.R;

public class Fragment04 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_04_list_activity, container, false);

        Button save = view.findViewById(R.id.btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //方案01
//                ((FragmentListActivity)getActivity()).popBackStack();

                //方案02
                startActivity(new Intent(getActivity(), FragmentListActivity.class));
                getActivity().finish();


            }
        });

        return view;
    }
}
