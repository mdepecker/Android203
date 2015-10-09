package fr.estei.fragmentlast;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import fr.estei.fragmentlast.data.FakeData;


public class TitlesFragment extends ListFragment {

    private OnTitleSelectedListener callback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, FakeData.titles));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (OnTitleSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Tu dois implementer" + activity.toString());
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        if (getFragmentManager().findFragmentById(R.id.title_fragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
            callback.onTitleSelected(position);
            getListView().setItemChecked(position, true);
    }

    /* Host activity must implement the following interfaces*/
    public interface OnTitleSelectedListener {
        void onTitleSelected(int index);
    }
}
