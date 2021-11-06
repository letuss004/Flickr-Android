package vn.edu.usth.flickr.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.usth.flickr.R;
import vn.edu.usth.flickr.adapter.SearchFragmentAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager recViewLM;
    private ArrayList<Drawable> imageList = new ArrayList<>();
    private ArrayList<String> imageLinkList = new ArrayList<>();
    private Context activityContext;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        activityContext = view.getContext();

        setUpRecyclerView(view);
        setUpImageList();
        return view;
    }


    private void setUpImageList() {
        imageLinkList.add("https://live.staticflickr.com/65535/51598697158_491af3e970_k.jpg");
        imageLinkList.add("https://live.staticflickr.com/65535/51596910674_eec7be4967_k.jpg");
        imageLinkList.add("https://live.staticflickr.com/65535/51584802722_250f1ca962_z.jpg");
        imageLinkList.add("https://live.staticflickr.com/65535/51573212984_1b7d87cf34_z.jpg");
        imageLinkList.add("https://live.staticflickr.com/65535/51464174186_e538c1c44c_h.jpg");
    }

    private void setUpRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.searchRecyclerView);

        recViewLM = new LinearLayoutManager(activityContext);
        recyclerView.setLayoutManager(recViewLM);

        adapter = new SearchFragmentAdapter(imageLinkList, this);
        recyclerView.setAdapter(adapter);
    }
}