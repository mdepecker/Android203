package fr.estei.fragmentlast;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

;import fr.estei.fragmentlast.data.FakeData;

/**
 * A placeholder fragment containing a simple view.
 */
public class ArticleFragment extends Fragment {
    final static String ARG_POSITION = "positiondelarticledanslaliste";
    int selectedTitle = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(savedInstanceState != null){
            selectedTitle = savedInstanceState.getInt(ARG_POSITION);
        }
        return inflater.inflate(R.layout.article_fragment, container,false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_POSITION,selectedTitle);
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle = getArguments();
        if(bundle != null){
            updateArticle(bundle.getInt(ARG_POSITION));
        }else if (selectedTitle != -1) {
            updateArticle(selectedTitle);
        };
    }
    public void updateArticle(int index){
        TextView article =  (TextView)getActivity().findViewById(R.id.article);
        article.setText(FakeData.articles[index]);
        selectedTitle = index;
    }


}
