package com.tasakos.tichucounter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogActivity extends AppCompatDialogFragment {
    EditText editname;
    private ExampleDialogListener listener;
    private TextView n1;
    private String title;
    private int layoutId;

    public DialogActivity(TextView n1, String title, int layoutId) {
        this.n1 = n1;
        this.title = title;
        this.layoutId = layoutId;
    }
    public DialogActivity(String title, int layoutId) {
        this.title = title;
        this.layoutId = layoutId;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(layoutId, null);

        builder.setView(view)
                .setTitle(title)
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (n1!=null){
                            String name = editname.getText().toString();
                            listener.applyTexts(name,n1);
                        }
                        else{
                            int points = Integer.parseInt(editname.getText().toString());
                            listener.setPoints(points);
                        }
                    }
                });

        editname = view.findViewById(R.id.editText2);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface ExampleDialogListener {
        void applyTexts(String name, TextView n);
        void setPoints(int points);
    }


}
