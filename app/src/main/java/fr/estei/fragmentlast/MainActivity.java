package fr.estei.fragmentlast;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity implements
        TitlesFragment.OnTitleSelectedListener,
        ArticleFragment.IOnButtonPush {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_article);
        /*Check if we are on two-fragment view or on a small device*/
        if(findViewById(R.id.Fragment_container) != null){
            /*Hey i'm a small device*/
            if(savedInstanceState != null){
                return;
            }
            TitlesFragment titleList = new TitlesFragment();
            getFragmentManager().beginTransaction()
                    .add(R.id.Fragment_container,titleList)
                    .commit();
        }
    }
    @Override
    public void onTitleSelected(int index) {
        ArticleFragment articleView = (ArticleFragment)getFragmentManager().findFragmentById(R.id.article_fragment);
        AuthorFragment authorView = (AuthorFragment) getFragmentManager().findFragmentById(R.id.author_fragment);

        if (articleView != null && authorView != null) {
            articleView.updateArticle(index);
            authorView.updateAuthor(index);
        }else{
            articleView = new ArticleFragment();
            Bundle args = new Bundle();
            args.putInt(ArticleFragment.ARG_POSITION,index);
            articleView.setArguments(args);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.Fragment_container,articleView);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    public void onShowAuthorButtonClick(int index) {
        /*todo */
        AuthorFragment authorFragment = (AuthorFragment) getFragmentManager().findFragmentById(R.id.author_fragment);
        if (authorFragment != null) {
            authorFragment.updateAuthor(index);
        } else {
            authorFragment = new AuthorFragment();
            Bundle args = new Bundle();
            args.putInt(ArticleFragment.ARG_POSITION, index);
            authorFragment.setArguments(args);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.Fragment_container, authorFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
