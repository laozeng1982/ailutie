package com.ailutie.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.ailutie.R;
import com.ailutie.activity.MainActivity;
import com.ailutie.constants.Constant;
import com.ailutie.datamodel.bean.User;
import com.ailutie.datamodel.json.GsonUtils;
import com.ailutie.tools.LogAndroid;

public class LoginFragment extends DialogFragment {

    private EditText mEditText_UserName;
    private EditText mEditText_UserAsscosiationName;
    private EditText mEditText_UserFirstPasswd;
    private EditText mEditText_UserSecondPasswd;

    private static User newUser = new User();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View settingLayout = inflater.inflate(R.layout.login_fragment,
                container, false);
        return settingLayout;
    }


    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        MainActivity.currFragTag = Constant.FRAGMENT_FLAG_SETTING;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.login_fragment, null);

        mEditText_UserName = (EditText) view.findViewById(R.id.editText_userName);
        mEditText_UserAsscosiationName = (EditText) view.findViewById(R.id.editText_userAssociationName);
        mEditText_UserFirstPasswd = (EditText) view.findViewById(R.id.editText_userFirstPasswd);
        mEditText_UserSecondPasswd = (EditText) view.findViewById(R.id.editText_userSecondPasswd);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
//								newUser.setId();
                                // TODO login logical rewrite
                                newUser.setName(mEditText_UserName.getText().toString());
                                newUser.setAssociation_name(mEditText_UserAsscosiationName.getText().toString());
                                newUser.setPassword(mEditText_UserFirstPasswd.getText().toString());
                                LogAndroid.e(newUser.getName() + ", " + newUser.getAssociation_name() + "," + newUser.getPassword());
                                if (mEditText_UserFirstPasswd.getText().toString().equals(mEditText_UserSecondPasswd.getText().toString())) {
                                    LogAndroid.e("Please make sure passwd are same!");
                                    Toast.makeText(MainActivity.getInstance(), "Please make sure passwd are same!", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                if (!GsonUtils.addUsers(Constant.USER_DATA_PATH, newUser)) {
                                    Toast.makeText(MainActivity.getInstance(), "User Name has been taken!", Toast.LENGTH_SHORT).show();
                                    LogAndroid.e("User Name has been taken!");
                                    return;
                                }
                            }
                        }).setNegativeButton("Cancel", null);
        return builder.create();
    }
}
