package com.mingkang.instagramclone;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {
    private EditText edtProfileName,edtFavouriteSport,edtProfession,edtBio,edtHobby;
    private Button btnUpdateInfo;

    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);
        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtFavouriteSport = view.findViewById(R.id.edtFavouriteSport);
        edtProfession = view.findViewById(R.id.edtProfession);
        edtBio = view.findViewById(R.id.edtBio);
        edtHobby = view.findViewById(R.id.edtHobby);
        btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);

        final ParseUser parseUser = ParseUser.getCurrentUser();
        if(parseUser.get("profileName") == null){
            parseUser.put("profileName","");
        }
        if(parseUser.get("bio") == null){
            parseUser.put("bio","");
        }
        if(parseUser.get("hobby") == null){
            parseUser.put("hobby","");
        }
        if(parseUser.get("profession") == null){
            parseUser.put("profession","");
        }
        if(parseUser.get("favouriteSport") == null){
            parseUser.put("favouriteSport","");
        }
        edtProfileName.setText(parseUser.get("profileName")+"");
        edtBio.setText(parseUser.get("bio")+"");
        edtHobby.setText(parseUser.get("hobby")+"");
        edtProfession.setText(parseUser.get("profession")+"");
        edtFavouriteSport.setText(parseUser.get("favouriteSport")+"");
        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseUser.put("profileName", edtProfileName.getText()+"");
                parseUser.put("bio", edtBio.getText().toString()+"");
                parseUser.put("favouriteSport", edtFavouriteSport.getText().toString()+"");
                parseUser.put("hobby", edtHobby.getText().toString()+"");
                parseUser.put("profession", edtProfession.getText().toString()+"");
                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Updating Info");
                progressDialog.show();
                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            if (e == null) {
                                FancyToast.makeText(getContext(),
                                        "info updated",
                                        Toast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                            } else {
                                FancyToast.makeText(getContext(),
                                        e.getMessage(),
                                        Toast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                            }
                            progressDialog.dismiss();
                        }
                    }
                });
            }
        });
        return view;
    }
}
