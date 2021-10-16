package vn.edu.usth.flickr;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        setSpanHyperText(view);
        // Inflate the layout for this fragment
        return view;
    }

    private void setSpanHyperText(View view) {
        TextView signingUp, loginHere;
        signingUp = view.findViewById(R.id.by_sign_up);
        loginHere = view.findViewById(R.id.login_here);

        String bySigningUp = "By signing up, you agree with Flick's Term of\nService and Privacy Policy.";
        SpannableString ssSigningUp = new SpannableString(bySigningUp);
        ForegroundColorSpan hyper_text = new ForegroundColorSpan(getResources().getColor(R.color.hyper_text));
        ForegroundColorSpan hyper_text1 = new ForegroundColorSpan(getResources().getColor(R.color.hyper_text));
        ssSigningUp.setSpan(hyper_text, 38, 53, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssSigningUp.setSpan(hyper_text1, 58, 72, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        String loginHereString = "Already a Flickr member? Log in here.";
        SpannableString ssLoginHere = new SpannableString(loginHereString);
        ClickableSpan loginHereClickable = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_fragment, new LoginFragment()).commit();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.hyper_text));
                ds.setUnderlineText(false);
            }
        };
//        ssLoginHere.setSpan(hyper_text, 25, 36, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssLoginHere.setSpan(loginHereClickable, 25, 36, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        signingUp.setText(ssSigningUp);
        loginHere.setText(ssLoginHere);
        loginHere.setMovementMethod(LinkMovementMethod.getInstance());
    }
}