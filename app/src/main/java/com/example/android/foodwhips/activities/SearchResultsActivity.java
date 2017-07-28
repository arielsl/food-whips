package com.example.android.foodwhips.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.foodwhips.MainActivity;
import com.example.android.foodwhips.R;
import com.example.android.foodwhips.adapters.RecipeResultsAdapter;
import com.example.android.foodwhips.models.SearchRecipe;
import com.example.android.foodwhips.utilities.NetworkUtils;
import com.example.android.foodwhips.utilities.SearchRecipeJsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class SearchResultsActivity extends BaseActivity {
    private ProgressBar bar;
    private RecyclerView mRecyclerView;
    private RecipeResultsAdapter startAdapter;
    private URL foodsUrl;

    private static final String TAG = "SearchResultsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        Bundle bundle = this.getIntent().getExtras();
        String searchQuery = bundle.getString("searchQuery");
        foodsUrl = NetworkUtils.buildUrl(searchQuery, 1);

        //Progress Bar
        bar = (ProgressBar) findViewById(R.id.progressBar);

        //RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(startAdapter);

        new FetchFoodTask().execute();

        Log.v(TAG, "HITTING THE SEARCHRESULTS ACTIVITY WITH QUERY: " + searchQuery);
    }


    private class FetchFoodTask extends AsyncTask<String, Void, ArrayList<SearchRecipe>> {
        @Override
        protected void onPreExecute(){
            bar.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<SearchRecipe> doInBackground(String... params) {
            ArrayList<SearchRecipe> recipeList = null;

            try {
                String jsonRecipeDataResponse = NetworkUtils.getResponseFromHttpUrl(foodsUrl);
                recipeList = SearchRecipeJsonUtils.parseJSON(jsonRecipeDataResponse);
            } catch (IOException e){
                e.printStackTrace();
            } catch (JSONException e){
                e.printStackTrace();
            }
            return recipeList;
        }

        @Override
        protected void onPostExecute(final ArrayList<SearchRecipe> recipeList) {
            super.onPostExecute(recipeList);

            bar.setVisibility(View.GONE);

            if (recipeList != null) {
                RecipeResultsAdapter adapter = new RecipeResultsAdapter(recipeList, new RecipeResultsAdapter.RecipeClickListener(){
                    @Override
                    public void onRecipeClick(int clickedItemIndex){
                        String recipeId = recipeList.get(clickedItemIndex).getId();
                        Log.v(TAG, "Le name: " + recipeId);

                        Intent switchAct = new Intent(SearchResultsActivity.this, RecipeDetailsActivity.class);
                        switchAct.putExtra("recipe_id", recipeId);
                        startActivity(switchAct);
                    }
                });
                mRecyclerView.setAdapter(adapter);
            }
        }
    }

}
