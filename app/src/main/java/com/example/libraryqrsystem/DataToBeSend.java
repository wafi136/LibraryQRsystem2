package com.example.libraryqrsystem;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DataToBeSend extends AppCompatDialogFragment {
    private EditText editTextUsername;
    private  EditText editTextPsd;
    private DisplayData listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_data_field,null);

        builder.setView(view)
                .setTitle("lOGIN")
                .setNegativeButton("Cancel and Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        System.exit(0);
                    }
                })
                //SINI TARUH CODE UPDATE KE FIREBASE
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String username = editTextUsername.getText().toString();
                        String password = editTextPsd.getText().toString();
                        listener.applyTexts(username,password);

                    }
                });
        editTextUsername = view.findViewById(R.id.field_Username);
        editTextPsd = view.findViewById(R.id.field_bookDetail);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (DisplayData) context;
        } catch (ClassCastException e) {
            throw  new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }

    }

    public interface DisplayData{
        void applyTexts (String username,String password );
    }


}
