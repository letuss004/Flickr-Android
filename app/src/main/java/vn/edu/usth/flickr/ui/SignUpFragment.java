package vn.edu.usth.flickr.ui;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.klinker.android.link_builder.Link;
import com.klinker.android.link_builder.LinkBuilder;

import java.util.Objects;

import vn.edu.usth.flickr.R;
import vn.edu.usth.flickr.db.DatabaseHelper;
import vn.edu.usth.flickr.repository.Data;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "SignUpFragment";
    private TextInputEditText email, pass;

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
        setSpanHyperText(view); // can be replace (optional), this is old one
        setHyperLink(view); // new one
        register(view);
        // Inflate the layout for this fragment
        return view;
    }


    private void register(View view) {
        Button button = view.findViewById(R.id.signUpButton);
        DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext(), "name", null, 21);
        pass = view.findViewById(R.id.emailSignUpEt);
        email = view.findViewById(R.id.passSignUpEt);

        button.setOnClickListener(v -> {
            String emailValue = Objects.requireNonNull(email.getText()).toString();
            String passValue = Objects.requireNonNull(pass.getText()).toString();

            boolean success = databaseHelper.registerUser(new Data(emailValue, passValue));
            if (success) {
                Log.e(TAG, "register: Register successful");
                getParentFragmentManager().popBackStack();
            } else {
                pass.setError("Something wrong");
                email.setError("Something wrong");
            }
        });
    }

    private void setHyperLink(View view) {
        TextView help, privacy, term;
        help = view.findViewById(R.id.tv_help_signup);
        privacy = view.findViewById(R.id.tv_privacy_signup);
        term = view.findViewById(R.id.tv_term_signup);

        Link helpLink = new Link("Help")
                .setTextColor(Color.GRAY)
                .setUnderlined(false)
                .setBold(false)
                .setOnClickListener(clickedText -> {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.flickrhelp.com/hc/en-us"));
                    startActivity(browserIntent);
                });

        // create the link builder object add the link rule
        LinkBuilder.on(help)
                .addLink(helpLink)
                .build(); // create the clickable links
    }

    // Hypertext but HyperLink also
    private void setSpanHyperText(View view) {
        TextView signingUp, loginHere;
        signingUp = view.findViewById(R.id.by_sign_up);
        loginHere = view.findViewById(R.id.login_here);

        String bySigningUp = "By signing up, you agree with Flick's Term of\nService and Privacy Policy.";
        SpannableString ssSigningUp = new SpannableString(bySigningUp);
        String loginHereString = "Already a Flickr member? Log in here.";
        SpannableString ssLoginHere = new SpannableString(loginHereString);

        ForegroundColorSpan hyper_text = new ForegroundColorSpan(getResources().getColor(R.color.hyper_text));
        ForegroundColorSpan hyper_text1 = new ForegroundColorSpan(getResources().getColor(R.color.hyper_text));


        ClickableSpan loginHereClickable = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_fragmentContainer, new LoginFragment()).commit();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.hyper_text));
                ds.setUnderlineText(false);
            }
        };

        ClickableSpan signUpClickable = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {

            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.hyper_text));
                ds.setUnderlineText(false);
            }
        };

        ClickableSpan signUpClickable1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {

            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.hyper_text));
                ds.setUnderlineText(false);
            }
        };

        ssSigningUp.setSpan(new URLSpan("https://www.flickrhelp.com/hc/en-us"), 38, 53, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssSigningUp.setSpan(new URLSpan("https://www.flickrhelp.com/hc/en-us"), 58, 72, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssSigningUp.setSpan(signUpClickable, 38, 53, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssSigningUp.setSpan(signUpClickable1, 58, 72, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssSigningUp.setSpan(hyper_text, 38, 53, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssSigningUp.setSpan(hyper_text1, 58, 72, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ssLoginHere.setSpan(loginHereClickable, 25, 36, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        signingUp.setText(ssSigningUp);
        loginHere.setText(ssLoginHere);
        loginHere.setMovementMethod(LinkMovementMethod.getInstance());
        signingUp.setMovementMethod(LinkMovementMethod.getInstance());

    }
}