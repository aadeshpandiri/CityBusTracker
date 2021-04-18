package com.example.CityBus;


import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SignupTabFragment extends Fragment {
    EditText username,UserLastName,UserCity,UserPhone;
    Button signin;
    float v = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment,container, false);

        username = root.findViewById(R.id.Name);
        UserLastName = root.findViewById(R.id.LastName);
        UserCity = root.findViewById(R.id.City);
        UserPhone = root.findViewById(R.id.Phone);
        signin = root.findViewById(R.id.sign_btn);

        username.setTranslationY(800);
        UserLastName.setTranslationY(800);
        UserCity.setTranslationY(800);
        UserPhone.setTranslationY(800);
        signin.setTranslationY(800);

        username.setAlpha(v);
        UserLastName.setAlpha(v);
        UserCity.setAlpha(v);
        UserPhone.setAlpha(v);
        signin.setAlpha(v);


        username.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        UserLastName.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        UserCity.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        UserPhone.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(600).start();
        signin.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();

        return root;
    }




}
