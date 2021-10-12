package vn.edu.usth.flickr;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private CheckBox checkBox;
    private TextView forgotPass;
    private View line;
    private TextInputLayout ctnPass;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        setInvisible(view);
        signUpOnClick(view);
        nextOnclick(view);
        // Inflate the layout for this fragment
        return view;
    }

    private void signUpOnClick(View view) {
        TextView textView = view.findViewById(R.id.sign_up);
        textView.setOnClickListener(v -> {
            assert getActivity() != null;
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_fragment, new SignUpFragment()).commit();
        });
    }

    private void nextOnclick(View view) {
        Button button = view.findViewById(R.id.nextButton);
        button.setOnClickListener(v -> {
            setVisible(view);
            button.setText(getResources().getText(R.string.login));
        });
    }

    private boolean isValidEmailAddress() {
        return false;
    }


    private void setInvisible(View view) {
        checkBox = view.findViewById(R.id.checkBox);
        forgotPass = view.findViewById(R.id.tv_forgot_pass);
        line = view.findViewById(R.id.line);
        ctnPass = view.findViewById(R.id.ctn_pass_login);
        ctnPass.setVisibility(View.GONE);

        checkBox.setVisibility(View.GONE);
        forgotPass.setVisibility(View.GONE);
        line.setVisibility(View.GONE);
    }

    private void setVisible(View view) {
        checkBox = view.findViewById(R.id.checkBox);
        forgotPass = view.findViewById(R.id.tv_forgot_pass);
        line = view.findViewById(R.id.line);
        ctnPass = view.findViewById(R.id.ctn_pass_login);

        ctnPass.setVisibility(View.VISIBLE);
        checkBox.setVisibility(View.VISIBLE);
        forgotPass.setVisibility(View.VISIBLE);
        line.setVisibility(View.VISIBLE);
    }

    private void setSpanText(View view) {

    }


}