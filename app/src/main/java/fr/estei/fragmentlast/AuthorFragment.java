package fr.estei.fragmentlast;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fr.estei.fragmentlast.data.FakeData;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthorFragment extends Fragment {
    private static final String ARG_POSITON = "authorquonaffiche";
    private int selectedAuthor = -1;


    public AuthorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_POSITON, selectedAuthor);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_author, container, false);
    }

    public void updateAuthor(int index) {
        TextView author = (TextView) getActivity().findViewById(R.id.author);
        author.setText(FakeData.authors[index]);
        selectedAuthor = index;

    }


}
