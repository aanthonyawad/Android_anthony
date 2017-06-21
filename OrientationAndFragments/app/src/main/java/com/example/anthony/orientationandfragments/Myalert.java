package com.example.anthony.orientationandfragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by anthony on 6/21/17.
 */

public class Myalert extends DialogFragment {


    public Dialog onCreateDialog(Bundle savedInstanceState)
    {

        AlertDialog.Builder mb = new AlertDialog.Builder(getActivity());
        mb.setIcon(R.drawable.ic_alarm_on_black_24dp);
        mb.setTitle("confirm");
        mb.setMessage("Are you sure you want to go to Activity 2?");
        mb.setPositiveButton("yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);}} );


        mb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();}});

        Dialog d = mb.create();
        return d;}

}
