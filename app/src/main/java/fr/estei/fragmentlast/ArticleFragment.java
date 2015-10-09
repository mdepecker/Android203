package fr.estei.fragmentlast;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import fr.estei.fragmentlast.data.FakeData;

/**
 * A placeholder fragment containing a simple view.
 */
public class ArticleFragment extends Fragment {
    final static String ARG_POSITION = "positiondelarticledanslaliste";
    private int selectedTitle = -1;
    private IOnButtonPush hostActivity;

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return View
     */
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
        outState.putInt(ARG_POSITION, selectedTitle);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = getActivity();
        try {
            hostActivity = (IOnButtonPush) activity;
        } catch (ClassCastException exception) {
            /*Todo inforlm that not works*/
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Button showAuthor = (Button) getActivity().findViewById(R.id.showAuthor_btn);

        Bundle bundle = getArguments();
        if(bundle != null){
            updateArticle(bundle.getInt(ARG_POSITION));
        }else if (selectedTitle != -1) {
            updateArticle(selectedTitle);
        }
        showAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hostActivity.onShowAuthorButtonClick(selectedTitle);
            }
        });
    }

    /**
     *
     * @param  index
     */
    public void updateArticle(int index){
        TextView article =  (TextView)getActivity().findViewById(R.id.article);
        article.setText(FakeData.articles[index]);
        selectedTitle = index;
    }

    public interface IOnButtonPush {
        void onShowAuthorButtonClick(int index);
    }


}
