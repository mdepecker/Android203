package fr.estei.fragmentlast;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import fr.estei.fragmentlast.data.FakeData;


public class TitlesFragment extends ListFragment {

    private OnTitleSelectedListener callback;

    /* Host activity must implement the following interfaces*/
    public  interface OnTitleSelectedListener {
        public void onTitleSelected(int index);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, FakeData.titles));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            callback = (OnTitleSelectedListener)activity;
        }catch (ClassCastException e ){
            throw new ClassCastException("Tu dois implementer" + activity.toString());
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        if(getFragmentManager().findFragmentById(R.id.title_fragment) != null){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
            callback.onTitleSelected(position);
            getListView().setItemChecked(position, true);
    }
}
