package com.example.hunnydates.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.hunnydates.R;
import com.example.hunnydates.utils.CurrentUser;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class EditClientFragment extends Fragment {

    private ImageView profileImage;
    private EditText usernameEditText;
    private EditText displayNameEditText;
    private EditText ageEditText;
    private EditText descriptionEditText;
    private Button editInfoButton;

    public EditClientFragment() {
    }

    public static EditClientFragment newInstance(String param1, String param2) {
        EditClientFragment fragment = new EditClientFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_client_profile_information, container, false);
        initializeComponents(view);

        editInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> data = new HashMap();
                data.put("display-name", displayNameEditText.getText().toString());
                data.put("username", usernameEditText.getText().toString());
                data.put("age", ageEditText.getText().toString());
                data.put("description", descriptionEditText.getText().toString());
                CurrentUser.getInstance().getDocument().update(data);
                CurrentUser.getInstance().queryProfileInfo();
                NavHostFragment.findNavController(getParentFragment()).popBackStack();
            }
        });

        return view;
    }

    private void initializeComponents(View view) {
        profileImage = view.findViewById(R.id.ec_profile_icon);
        displayNameEditText = view.findViewById(R.id.ec_name_edit_text);
        usernameEditText = view.findViewById(R.id.ec_username_edit_text);
        ageEditText = view.findViewById(R.id.ec_age_edit_text);
        descriptionEditText = view.findViewById(R.id.ec_description_edit_text);
        editInfoButton = view.findViewById(R.id.ec_create_account_button);

        String profilePicURL = CurrentUser.getInstance().getPhotoURL().replace("s96", "s384");
        Picasso.get().load(profilePicURL).into(profileImage);
        displayNameEditText.setText(CurrentUser.getInstance().getDisplayName());
        usernameEditText.setText(CurrentUser.getInstance().getUsername());
        ageEditText.setText(CurrentUser.getInstance().getAge());
        descriptionEditText.setText(CurrentUser.getInstance().getDescription());
    }
}
